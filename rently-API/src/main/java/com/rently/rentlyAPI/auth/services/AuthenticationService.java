package com.rently.rentlyAPI.auth.services;

import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
import com.rently.rentlyAPI.auth.dto.LoginRequestDto;
import com.rently.rentlyAPI.dto.RootUserDto;
import jakarta.servlet.http.HttpServletResponse;

public interface AuthenticationService {
     
     AuthenticationResponseDto authenticate(LoginRequestDto loginRequestDto, HttpServletResponse response);
     
     String changePassword(String token, ChangePasswordRequestDto changePasswordRequestDto);
     
     AuthenticationResponseDto buildAuthenticationResponse(String jwtToken, String refreshToken, RootUserDto userDto);
}
