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
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.io.IOException;

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
  
  public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException {
    
    String authHeader = request.getHeader(HttpHeaders.AUTHORIZATION);
    if (authHeader == null || !authHeader.startsWith("Bearer ")) {
      response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
      return;
    }
    
    String refreshToken = authHeader.substring(7);
    String userEmail = jwtUtils.extractUsername(refreshToken);
    
    if (userEmail != null) {
      User user = userRepository.findByEmail(userEmail)
          .orElseThrow(() -> new UsernameNotFoundException("User not found"));
      RefreshToken refreshTokenEntity = refreshTokenRepository.findByRefreshToken(refreshToken).orElseThrow();
      
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
      
      AuthenticationResponseDto authResponse = buildAuthenticationResponse(accessToken, newRefreshToken, null);
      new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
    } else {
      response.setStatus(HttpServletResponse.SC_FORBIDDEN);
    }
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
