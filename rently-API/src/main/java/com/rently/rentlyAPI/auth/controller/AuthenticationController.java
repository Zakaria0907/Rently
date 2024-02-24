package com.rently.rentlyAPI.auth.controller;


import com.rently.rentlyAPI.auth.domain.dto.AuthenticationRequestDto;
import com.rently.rentlyAPI.auth.domain.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.domain.dto.UserDto;
import com.rently.rentlyAPI.auth.service.AuthenticationService;
import com.rently.rentlyAPI.auth.domain.dto.RegisterRequestDto;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
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

  private final AuthenticationService service;

  @PostMapping("/register")
  public ResponseEntity<UserDto> register(
     @Valid @RequestBody RegisterRequestDto request
  ) {
    return ResponseEntity.ok(service.register(request));
  }
  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDto> authenticate(
      @RequestBody AuthenticationRequestDto request,
       HttpServletResponse response
  ) {
    return ResponseEntity.ok(service.authenticate(request, response));
  }

  @PostMapping("/refresh-token")
  public void refreshToken( HttpServletRequest request, HttpServletResponse response, String yry , String sdfds) throws IOException {
    service.refreshToken(request, response);
  }


}
