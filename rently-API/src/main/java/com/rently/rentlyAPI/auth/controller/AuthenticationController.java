package com.rently.rentlyAPI.auth.controller;


import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.dto.LoginRequestDto;
import com.rently.rentlyAPI.auth.services.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api/v1/auth")
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

//  @PostMapping("/refresh-token")
//  public void refreshToken( HttpServletRequest request, HttpServletResponse response, String yry , String sdfds) throws IOException {
//    service.refreshToken(request, response);
//  }


}
