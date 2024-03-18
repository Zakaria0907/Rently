//package com.rently.rentlyAPI.controller.auth;
//
//import com.rently.rentlyAPI.auth.controller.AuthenticationController;
//import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
//import com.rently.rentlyAPI.auth.dto.LoginRequestDto;
//import com.rently.rentlyAPI.auth.dto.RegisterRequestDto;
//import com.rently.rentlyAPI.auth.services.AuthenticationServiceImpl;
//import com.rently.rentlyAPI.dto.UserDto;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.Test;
//import org.mockito.Mockito;
//import org.springframework.http.HttpStatus;
//import org.springframework.http.ResponseEntity;
//
//import java.io.IOException;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class AuthenticationControllerTest {
//
//    AuthenticationServiceImpl mockedAuthenticationServiceImpl = mock(AuthenticationServiceImpl.class);
//    RegisterRequestDto mockedRegisterRequestDto = mock(RegisterRequestDto.class);
//    UserDto mockedUserDto = mock(UserDto.class);
//    HttpServletResponse mockedHttpServletResponse = mock(HttpServletResponse.class);
//    LoginRequestDto mockedLoginRequestDto = mock(LoginRequestDto.class);
//    AuthenticationResponseDto mockedAuthenticationResponseDto = mock(AuthenticationResponseDto.class);
//    HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);
//    @Test
//    public void testRegister() {
//        // Arrange
//        AuthenticationController authenticationController = new AuthenticationController(mockedAuthenticationServiceImpl);
//        when(mockedAuthenticationServiceImpl.register(mockedRegisterRequestDto)).thenReturn(mockedUserDto);
//
//        // Act
//        ResponseEntity<UserDto> testResponseEntity = authenticationController.register(mockedRegisterRequestDto);
//
//        // Assert
//        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
//        assertEquals(ResponseEntity.ok(mockedUserDto), testResponseEntity);
//    }
//
//    @Test
//    public void testAuthenticate() {
//        // Arrange
//        AuthenticationController authenticationController = new AuthenticationController(mockedAuthenticationServiceImpl);
//        when(mockedAuthenticationServiceImpl.authenticate(mockedLoginRequestDto, mockedHttpServletResponse)).thenReturn(mockedAuthenticationResponseDto);
//
//        // Act
//        ResponseEntity<AuthenticationResponseDto> testResponseEntity = authenticationController.authenticate(mockedLoginRequestDto, mockedHttpServletResponse);
//
//        // Assert
//        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
//        assertEquals(ResponseEntity.ok(mockedAuthenticationResponseDto), testResponseEntity);
//    }
//
//    @Test
//    public void testRefreshToken() throws IOException {
//        // Arrange
//        String yry = "bing", sdfds = "bong";
//        AuthenticationController authenticationController = new AuthenticationController(mockedAuthenticationServiceImpl);
//
//        // Act
//        authenticationController.refreshToken(mockedHttpServletRequest, mockedHttpServletResponse, yry, sdfds);
//
//        // Assert
//        Mockito.verify(mockedAuthenticationServiceImpl).refreshToken(mockedHttpServletRequest, mockedHttpServletResponse);
//    }
//
//}
