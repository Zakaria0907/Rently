package com.rently.rentlyAPI.auth.controller;


import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
import com.rently.rentlyAPI.auth.dto.LoginRequestDto;
import com.rently.rentlyAPI.auth.services.Impl.AuthenticationServiceImpl;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("/api/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

  private final AuthenticationServiceImpl service;


  @PostMapping("/authenticate")
  public ResponseEntity<AuthenticationResponseDto> authenticate(
          @RequestBody LoginRequestDto request,
          HttpServletResponse response
  ) {
    return ResponseEntity.ok(service.authenticate(request, response));
  }
  
  @PostMapping("/change-password")
  public ResponseEntity<String> changePassword(@RequestHeader("Authorization") String token, @RequestBody ChangePasswordRequestDto changePasswordRequestDto) {
    return ResponseEntity.ok(service.changePassword(token, changePasswordRequestDto));
  }

 @PostMapping("/refresh-token")
 public void refreshToken( HttpServletRequest request, HttpServletResponse response, String yry , String sdfds) throws IOException, IOException {
   service.refreshToken(request, response);
 }


}
