package com.rently.rentlyAPI.services.auth;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.rently.rentlyAPI.dto.auth.AuthenticationRequestDto;
import com.rently.rentlyAPI.dto.auth.AuthenticationResponseDto;
import com.rently.rentlyAPI.dto.auth.RegisterRequestDto;
import com.rently.rentlyAPI.dto.UserDto;
import com.rently.rentlyAPI.entity.auth.RefreshToken;
import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.entity.auth.Provider;
import com.rently.rentlyAPI.repository.auth.RefreshTokenRepository;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.security.utils.JwtUtils;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.services.UserService;
import com.rently.rentlyAPI.services.impl.UserServiceImpl;
import com.rently.rentlyAPI.validators.ObjectsValidator;
import jakarta.persistence.EntityNotFoundException;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

/**
 * The AuthenticationService class is responsible for handling user authentication and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationService {
  private final UserRepository userRepository;
  private final JwtUtils jwtUtils;
  private final RefreshTokenRepository refreshTokenRepository;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final TokenService tokenService;
  private final ObjectsValidator<Object> validator;
  private final UserServiceImpl userServiceImpl;
  
  // Main service methods
  public UserDto register(RegisterRequestDto registerRequestDto) {
    validator.validate(registerRequestDto);
    User user = userService.findByEmail(registerRequestDto.getEmail()).orElse(null);

    if (user != null) {
     throw new AuthenticationException("This email is already associated with an account");
      
    }
    User savedUser = userServiceImpl.createUser(registerRequestDto);
    return UserDto.fromEntity(savedUser);
  }
  
  public AuthenticationResponseDto authenticate(AuthenticationRequestDto request, HttpServletResponse response) {
    User user = userService.findByEmail(request.getEmail())
        .orElseThrow(() -> new EntityNotFoundException("User " +request.getEmail() + " not found"));
    
    // Check if the user's email is associated with a third party provider
    if (!user.getProvider().equals(Provider.RENTLY) && (request.getPassword() != null)) {
      // Return a forbidden response with the appropriate message
      throw new AuthenticationException("This email is already associated with an account using a third-party provider: Google");
    }
    // TODO: handle these cases
    // 1. User does not exist
    
    // Proceed with authentication
    authenticationManager.authenticate(
        new UsernamePasswordAuthenticationToken(
            request.getEmail(),
            request.getPassword()
        )
    );
    
    String jwtToken = jwtUtils.generateToken(user);
    String refreshToken = jwtUtils.generateRefreshToken(user);
    tokenService.revokeAllUserTokens(user);
    tokenService.revokeAllUserRefreshTokens(user);
    tokenService.saveUserTokens(user, jwtToken, refreshToken);

    jwtUtils.addTokensAsCookies(response, jwtToken, refreshToken);
    UserDto userDto = UserDto.fromEntity(user);
    return buildAuthenticationResponse(jwtToken, refreshToken, userDto);
  }
  

  /**
 * The refreshToken method is responsible for refreshing the user's access token, the user refresh token is used to generate a new access token and refresh token.
 * We retrieve the refresh token from the request cookies and use it to get the user's email.
 * We then use the user's email to get the user from the database.
 * The old tokens are revoked and the new tokens are saved in the database and sent as cookies in the response.
 * @param request The HttpServletRequest object
 * @param response The HttpServletResponse object
 * @throws IOException If an input or output exception occurs
 * @throws UsernameNotFoundException If the user is not found
 * @throws EntityNotFoundException If the refresh token is not found
 * @throws AuthenticationException If the refresh token is revoked or invalid
 */
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    Cookie[] cookies = request.getCookies();

    String refreshToken = null;
    if (cookies != null) {
      for (Cookie cookie : cookies) {
        if (cookie.getName().equals("refreshToken")) {
          refreshToken = cookie.getValue();
          break;
        }
      }
    }

    if (refreshToken == null) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }

    String userEmail = jwtUtils.extractUsername(refreshToken);
    System.out.println("userEmail in AuthenticationService: " + userEmail);

    User user = userRepository.findByEmail(userEmail)
        .orElseThrow(() -> new UsernameNotFoundException("User not found"));

    System.out.println("user in AuthenticationService: " + user.getRole());

    RefreshToken refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken)
        .orElseThrow();

    if (refreshTokenEntity.isRevoked() || !jwtUtils.isTokenValid(refreshToken, user)) {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
      return;
    }

    tokenService.revokeAllUserTokens(user);
    tokenService.revokeAllUserRefreshTokens(user);

    String accessToken = jwtUtils.generateToken(user);
    String newRefreshToken = jwtUtils.generateRefreshToken(user);

    tokenService.saveUserTokens(user, accessToken, newRefreshToken);

    jwtUtils.addTokensAsCookies(response, accessToken, newRefreshToken);

    AuthenticationResponseDto authResponse = buildAuthenticationResponse(accessToken, newRefreshToken, UserDto.fromEntity(user));
    new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
  }
  
  // Helper methods
  private AuthenticationResponseDto buildAuthenticationResponse(String jwtToken, String refreshToken, UserDto userDto) {
    return AuthenticationResponseDto.builder()
        .accessToken(jwtToken)
        .refreshToken(refreshToken)
        .user(userDto)
        .build();
  }
  
}
