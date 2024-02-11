package com.rently.rentlyAPI.auth.service;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.rently.rentlyAPI.auth.domain.entity.token.AccessToken;
import com.rently.rentlyAPI.auth.domain.entity.RegisterRequest;
import com.rently.rentlyAPI.auth.domain.entity.AuthenticationResponse;
import com.rently.rentlyAPI.auth.domain.entity.token.RefreshToken;
import com.rently.rentlyAPI.auth.domain.entity.AuthenticationRequest;
import com.rently.rentlyAPI.auth.domain.enums.TokenType;
import com.rently.rentlyAPI.auth.repository.RefreshTokenRepository;
import com.rently.rentlyAPI.auth.repository.TokenRepository;
import com.rently.rentlyAPI.auth.util.JwtUtils;
import com.rently.rentlyAPI.auth.domain.enums.Role;
import com.rently.rentlyAPI.auth.domain.entity.User;
import com.rently.rentlyAPI.auth.domain.dto.UserDto;
import com.rently.rentlyAPI.auth.repository.UserRepository;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import jakarta.servlet.http.Cookie;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository repository;
  private final TokenRepository tokenRepository;
  private final PasswordEncoder passwordEncoder;
  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;
  private final RefreshTokenRepository refreshTokenRepository;

  public AuthenticationResponse register(RegisterRequest request) {
    var user = User.builder()
        .firstname(request.getFirstname())
        .lastname(request.getLastname())
        .email(request.getEmail())
        .password(passwordEncoder.encode(request.getPassword()))
        .role(Role.USER) //set default role to USER
        .build();
    var savedUser = repository.save(user);
    var jwtToken = jwtUtils.generateToken(user);
    var refreshToken = jwtUtils.generateRefreshToken(user);
    saveUserToken(savedUser, jwtToken, refreshToken);
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
        .build();
  }

  public AuthenticationResponse authenticate(AuthenticationRequest request) {
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    var user = repository.findByEmail(request.getEmail())
        .orElseThrow();
    var jwtToken = jwtUtils.generateToken(user);
    var refreshToken = jwtUtils.generateRefreshToken(user);
    revokeAllUserRefreshTokens(user);
    revokeAllUserTokens(user);
    saveUserToken(user, jwtToken, refreshToken);
    var userDto = UserDto.builder()
        .id(user.getId())
        .firstname(user.getFirstname())
        .lastname(user.getLastname())
        .email(user.getEmail())
        .role(user.getRole().name())
        .build();
    return AuthenticationResponse.builder()
        .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .user(userDto)
        .build();
  }

  @Transactional
  public void saveUserToken(User user, String jwtToken, String refreshToken) {
    var token = AccessToken.builder()
            .user(user)
            .token(jwtToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();

    // Save the Token (which will cascade to RefreshToken)
    tokenRepository.save(token);

    var refreshTokenEntity = RefreshToken.builder()
            .accessToken(token)
            .refreshToken(refreshToken)
            .tokenType(TokenType.BEARER)
            .expired(false)
            .revoked(false)
            .build();

    refreshTokenRepository.save(refreshTokenEntity);
  }


  private void revokeAllUserTokens(User user) {
    var validUserTokens = tokenRepository.findAllValidTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(accessToken -> {
      accessToken.setExpired(true);
      accessToken.setRevoked(true);
    });
    tokenRepository.saveAll(validUserTokens);
  }

  private void revokeAllUserRefreshTokens(User user) {
    var validUserTokens = refreshTokenRepository.findAllValidRefreshTokenByUser(user.getId());
    if (validUserTokens.isEmpty())
      return;
    validUserTokens.forEach(token -> {
      token.setExpired(true);
      token.setRevoked(true);
    });
    refreshTokenRepository.saveAll(validUserTokens);
  }

  public void refreshToken(
          HttpServletRequest request,
          HttpServletResponse response
  ) throws IOException {
    final String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    final String refreshToken;
    final String userEmail;
    
    if (authHeader == null ||!authHeader.startsWith("Bearer ")) {
      return;
    }
    
    refreshToken = authHeader.substring(7);
    userEmail = jwtUtils.extractUsername(refreshToken);
    
    if (userEmail != null) {
      var user = this.repository.findByEmail(userEmail)
              .orElseThrow();
      var refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken)
              .orElseThrow();
      
      // Check if the refresh token is revoked
      if (refreshTokenEntity.isRevoked()) {
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
        return;
      }
      
      if (jwtUtils.isTokenValid(refreshToken, user)) {
        var accessToken = jwtUtils.generateToken(user);
        var newRefreshToken = jwtUtils.generateRefreshToken(user);
        revokeAllUserRefreshTokens(user);
        revokeAllUserTokens(user);
        saveUserToken(user, accessToken, newRefreshToken);

        // Set the refresh token as an HTTP cookie
        Cookie refreshTokenCookie = new Cookie("refreshToken", newRefreshToken);
        refreshTokenCookie.setMaxAge(60 * 60 * 24 * 30); // 30 days
        refreshTokenCookie.setHttpOnly(true);
        refreshTokenCookie.setSecure(true); // Use 'false' if not using HTTPS
        refreshTokenCookie.setPath("/"); // Set the cookie path

        response.addCookie(refreshTokenCookie);


        var authResponse = AuthenticationResponse.builder()
                .accessToken(accessToken)
                .refreshToken(newRefreshToken)
                .build();
        new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
      
      } else {
        // Handle case where the refresh token is expired
        response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      }
    
    } else {
      // Handle case where userEmail could not be extracted
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
  }
}
