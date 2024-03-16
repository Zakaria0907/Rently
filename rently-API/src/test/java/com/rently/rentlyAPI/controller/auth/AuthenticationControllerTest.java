package com.rently.rentlyAPI.controller.auth;

import com.rently.rentlyAPI.auth.controller.AuthenticationController;
import com.rently.rentlyAPI.dto.UserDto;
import com.rently.rentlyAPI.auth.dto.AuthenticationRequestDto;
import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.dto.RegisterRequestDto;
import com.rently.rentlyAPI.auth.services.AuthenticationService;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;


import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class AuthenticationControllerTest {

    AuthenticationService mockedAuthenticationService = mock(AuthenticationService.class);
    RegisterRequestDto mockedRegisterRequestDto = mock(RegisterRequestDto.class);
    UserDto mockedUserDto = mock(UserDto.class);
    HttpServletResponse mockedHttpServletResponse = mock(HttpServletResponse.class);
    AuthenticationRequestDto mockedAuthenticationRequestDto = mock(AuthenticationRequestDto.class);
    AuthenticationResponseDto mockedAuthenticationResponseDto = mock(AuthenticationResponseDto.class);
    HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);
    @Test
    public void testRegister() {
        // Arrange
        AuthenticationController authenticationController = new AuthenticationController(mockedAuthenticationService);
        when(mockedAuthenticationService.register(mockedRegisterRequestDto)).thenReturn(mockedUserDto);

        // Act
        ResponseEntity<UserDto> testResponseEntity = authenticationController.register(mockedRegisterRequestDto);

        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(mockedUserDto), testResponseEntity);
    }

    @Test
    public void testAuthenticate() {
        // Arrange
        AuthenticationController authenticationController = new AuthenticationController(mockedAuthenticationService);
        when(mockedAuthenticationService.authenticate(mockedAuthenticationRequestDto, mockedHttpServletResponse)).thenReturn(mockedAuthenticationResponseDto);

        // Act
        ResponseEntity<AuthenticationResponseDto> testResponseEntity = authenticationController.authenticate(mockedAuthenticationRequestDto, mockedHttpServletResponse);

        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok(mockedAuthenticationResponseDto), testResponseEntity);
    }

    @Test
    public void testRefreshToken() throws IOException {
        // Arrange
        String yry = "bing", sdfds = "bong";
        AuthenticationController authenticationController = new AuthenticationController(mockedAuthenticationService);

        // Act
        authenticationController.refreshToken(mockedHttpServletRequest, mockedHttpServletResponse, yry, sdfds);

        // Assert
        Mockito.verify(mockedAuthenticationService).refreshToken(mockedHttpServletRequest, mockedHttpServletResponse);
    }

}
