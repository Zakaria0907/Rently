//package com.rently.rentlyAPI.security.utils;
//
//import com.rently.rentlyAPI.entity.User;
//import com.rently.rentlyAPI.entity.auth.Provider;
//import com.rently.rentlyAPI.entity.auth.RentlyOAuth2User;
//import com.rently.rentlyAPI.services.UserService;
//import com.rently.rentlyAPI.services.auth.TokenService;
//import com.rently.rentlyAPI.services.impl.UserServiceImpl;
//import jakarta.servlet.ServletException;
//import jakarta.servlet.http.HttpServletRequest;
//import jakarta.servlet.http.HttpServletResponse;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.core.Authentication;
//
//import java.io.IOException;
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertNotNull;
//import static org.mockito.Mockito.*;
//
//public class OAuth2LoginSuccessHandlerTest {
//
//    UserService mockedUserService = mock(UserService.class);
//    JwtUtils mockedJwtUtils = mock(JwtUtils.class);
//    TokenService mockedTokenService = mock(TokenService.class);
//    UserServiceImpl mockedUserServiceImpl = mock(UserServiceImpl.class);
//    HttpServletResponse mockedHttpServletResponse = mock(HttpServletResponse.class);
//    HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);
//    Authentication mockedAuthentication = mock(Authentication.class);
//    User mockedOptionalUser = mock(User.class);
//    User mockedUser = mock(User.class);
//    RentlyOAuth2User mockedRentlyOAuth2User = mock(RentlyOAuth2User.class);
//
//    @Test
//    public void testAllArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        OAuth2LoginSuccessHandler testOAuth2LoginSuccessHandler = new OAuth2LoginSuccessHandler(mockedUserService, mockedJwtUtils, mockedTokenService, mockedUserServiceImpl);
//
//        // Assert
//        assertNotNull(testOAuth2LoginSuccessHandler);
//
//
//    }
//
//
//    @Test
//    public void testonAuthenticationSuccess() throws ServletException, IOException {
//        // Arrange
//        Optional<User> bingbong;
//        OAuth2LoginSuccessHandler spyOAuth2LoginSuccessHandler = new OAuth2LoginSuccessHandler(mockedUserService, mockedJwtUtils, mockedTokenService, mockedUserServiceImpl);
//        when(mockedAuthentication.getPrincipal()).thenReturn(mockedRentlyOAuth2User);
//        when(mockedUserService.findByEmail(any(String.class))).thenReturn(bingbong = Optional.of(mockedOptionalUser));
//        when(bingbong.isPresent()).thenReturn(true);
//        when(bingbong.get()).thenReturn(mockedUser);
//        when(mockedUser.getProvider()).thenReturn(Provider.GOOGLE);
//        when(mockedJwtUtils.generateToken(mockedUser)).thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
//        when(mockedJwtUtils.generateRefreshToken(mockedUser)).thenReturn("eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJzdWIiOiIxMjM0NTY3ODkwIiwibmFtZSI6IkpvaG4gRG9lIiwiaWF0IjoxNTE2MjM5MDIyfQ.SflKxwRJSMeKKF2QT4fwpMeJf36POk6yJV_adQssw5c");
//
//        // Act
//        spyOAuth2LoginSuccessHandler.onAuthenticationSuccess(mockedHttpServletRequest, mockedHttpServletResponse, mockedAuthentication);
//
//        // Assert
//        verify(spyOAuth2LoginSuccessHandler).onAuthenticationSuccess(mockedHttpServletRequest, mockedHttpServletResponse, mockedAuthentication);
//
//
//    }
//
//}
