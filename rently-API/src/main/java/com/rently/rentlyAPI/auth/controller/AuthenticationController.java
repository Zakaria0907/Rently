package com.rently.rentlyAPI.auth.controller;


import com.rently.rentlyAPI.auth.domain.dto.AuthenticationRequestDto;
import com.rently.rentlyAPI.auth.domain.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.service.AuthenticationService;
import com.rently.rentlyAPI.auth.domain.dto.RegisterRequestDto;
import com.rently.rentlyAPI.auth.service.LogoutService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;

@RestController
@RequestMapping("/api/v1/auth")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationService authenticationService;
  private final LogoutService logoutService;

  @PostMapping("/register")
  public ResponseEntity<AuthenticationResponseDto> register(
      @RequestBody RegisterRequestDto request
  ) {
    return ResponseEntity.ok(authenticationService.register(request));
  }
  
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDto> authenticate(
      @RequestBody AuthenticationRequestDto request
  ) {
    return ResponseEntity.ok(authenticationService.authenticate(request));
  }
  
  @PostMapping("/refresh-token")
  public void refreshToken(
      HttpServletRequest request,
      HttpServletResponse response
  ) throws IOException {
    authenticationService.refreshToken(request, response);
  }


}
