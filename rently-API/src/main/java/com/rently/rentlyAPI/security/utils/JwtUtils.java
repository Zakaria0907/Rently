package com.rently.rentlyAPI.security.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletResponse;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Set;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class JwtUtils {

  @Value("${application.security.jwt.secret-key}")
  private String secretKey;
  
  @Value("${application.security.jwt.expiration}")
  private long jwtExpiration;
  
  @Value("${application.security.jwt.refresh-token.expiration}")
  private long refreshExpiration;

  public String generateToken(UserDetails userDetails) {
    return generateToken(new HashMap<>(), userDetails);
  }

  public String generateToken(Map<String, Object> extraClaims, UserDetails userDetails) {
    Set<String> roles = userDetails.getAuthorities()
        .stream()
        .map(GrantedAuthority::getAuthority)
        .collect(Collectors.toSet());

    extraClaims.put("roles", roles);
    return buildToken(extraClaims, userDetails, jwtExpiration);
  }

  public String generateRefreshToken (UserDetails userDetails) {
    return buildToken(new HashMap<>(), userDetails, refreshExpiration);
  }

  private String buildToken( Map<String, Object> extraClaims, UserDetails userDetails, long expiration) {
    return Jwts
            .builder()
            .setClaims(extraClaims)
            .setSubject(userDetails.getUsername())
            .setIssuedAt(new Date(System.currentTimeMillis()))
            .setExpiration(new Date(System.currentTimeMillis() + expiration))
            .signWith(getSignInKey(), SignatureAlgorithm.HS256)
            .compact();
  }
  
  public String extractUsername(String token) {
    return extractClaim(token, Claims::getSubject);
  }
  
  public <T> T extractClaim(String token, Function<Claims, T> claimsResolver) {
    final Claims claims = extractAllClaims(token);
    return claimsResolver.apply(claims);
  }
  
  private Date extractExpiration(String token) {
    return extractClaim(token, Claims::getExpiration);
  }
  
  private Claims extractAllClaims(String token) {
    return Jwts
        .parserBuilder()
        .setSigningKey(getSignInKey())
        .build()
        .parseClaimsJws(token)
        .getBody();
  }

  public boolean isTokenValid(String token, UserDetails userDetails) {
    final String username = extractUsername(token);
    return (username.equals(userDetails.getUsername())) && !isTokenExpired(token);
  }

  private boolean isTokenExpired(String token) {
    return extractExpiration(token).before(new Date());
  }
  
  private Key getSignInKey() {
    byte[] keyBytes = Decoders.BASE64.decode(secretKey);
    return Keys.hmacShaKeyFor(keyBytes);
  }
  
  
  /**
   * Adds JWT and Refresh tokens as cookies to the HTTP response.
   *
   * @param response The HttpServletResponse to which the cookies will be added.
   * @param jwtToken The JWT token string.
   * @param refreshToken The Refresh token string.
   */
  public void addTokensAsCookies(HttpServletResponse response, String jwtToken, String refreshToken) {
    // Create and configure the JWT token cookie
    Cookie jwtTokenCookie = new Cookie("accessToken", jwtToken);
    jwtTokenCookie.setMaxAge(24 * 60 * 60); // 7 days in seconds
    jwtTokenCookie.setHttpOnly(true);
    jwtTokenCookie.setSecure(true);
    jwtTokenCookie.setPath("/");
    
    // Create and configure the refresh token cookie
    Cookie refreshTokenCookie = new Cookie("refreshToken", refreshToken);
    refreshTokenCookie.setMaxAge(24 * 60 * 60); // 30 days in seconds
    refreshTokenCookie.setHttpOnly(true);
    refreshTokenCookie.setSecure(true);
    refreshTokenCookie.setPath("/");
    
    // Add cookies to the response
    response.addCookie(jwtTokenCookie);
    response.addCookie(refreshTokenCookie);
  }
  
}
