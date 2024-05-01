package com.rently.rentlyAPI.auth.controller;

import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
import com.rently.rentlyAPI.auth.dto.LoginRequestDto;
import com.rently.rentlyAPI.auth.services.Impl.AuthenticationServiceImpl;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

public class AuthenticationControllerTest {

    @Mock
    private AuthenticationServiceImpl authenticationService;

    @InjectMocks
    private AuthenticationController authenticationController;

    @Mock
    private HttpServletResponse httpServletResponse;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    public void testAuthenticate() {
        LoginRequestDto requestDto = new LoginRequestDto();
        AuthenticationResponseDto responseDto = new AuthenticationResponseDto();
        when(authenticationService.authenticate(requestDto, httpServletResponse)).thenReturn(responseDto);

        ResponseEntity<AuthenticationResponseDto> responseEntity = authenticationController.authenticate(requestDto, httpServletResponse);

        assertEquals(responseDto, responseEntity.getBody());
    }

    @Test
    public void testChangePassword() {
        String token = "dummyToken";
        ChangePasswordRequestDto requestDto = new ChangePasswordRequestDto("oldPassword", "newPassword", "newPassword");
        when(authenticationService.changePassword(token, requestDto)).thenReturn("Password changed successfully");

        ResponseEntity<String> responseEntity = authenticationController.changePassword(token, requestDto);

        assertEquals("Password changed successfully", responseEntity.getBody());
    }

    @Test
    public void testRefreshToken() throws IOException {
        HttpServletRequest httpServletRequest = mock(HttpServletRequest.class);
        doNothing().when(authenticationService).refreshToken(httpServletRequest, httpServletResponse);

        authenticationController.refreshToken(httpServletRequest, httpServletResponse, "", "");

        verify(authenticationService, times(1)).refreshToken(httpServletRequest, httpServletResponse);
    }
}
