//package com.rently.rentlyAPI.security.config;
//
//import com.rently.rentlyAPI.security.filter.JwtAuthenticationFilter;
//import jakarta.servlet.Filter;
//import jakarta.servlet.http.HttpServletRequest;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.authentication.AuthenticationProvider;
//import org.springframework.security.config.Customizer;
//import org.springframework.security.config.annotation.web.builders.HttpSecurity;
//import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
//import org.springframework.security.config.annotation.web.configurers.CorsConfigurer;
//import org.springframework.security.config.annotation.web.configurers.CsrfConfigurer;
//import org.springframework.security.web.SecurityFilterChain;
//import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
//import org.springframework.security.web.authentication.logout.LogoutHandler;
//import org.springframework.web.cors.CorsConfigurationSource;
//
//import java.util.List;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.*;
//
//public class WebSecurityConfigTest {
//
//    JwtAuthenticationFilter mockedJwtAuthenticationFilter  = mock(JwtAuthenticationFilter.class);
//    AuthenticationProvider mockedAuthenticationProvider = mock(AuthenticationProvider.class);
//    LogoutHandler mockedLogoutHandler = mock(LogoutHandler.class);
//    HttpSecurity mockedHttpSecurity = mock(HttpSecurity.class);
//    Customizer<CorsConfigurer<HttpSecurity>> mockedCorsCustomizer = mock(Customizer.class);
//    CorsConfigurer<HttpSecurity> mockedCorsConfigurer = mock(CorsConfigurer.class);
//    CorsConfigurer mockedCorsConfigurerRaw = mock(CorsConfigurer.class);
//    CorsConfigurationSource mockedCorsConfigurationSource = mock(CorsConfigurationSource.class);
//    Customizer<CsrfConfigurer<HttpSecurity>> mockedCsrfCustomizer = mock(Customizer.class);
//
//    @Test
//    public void testWebSecurityConfig() throws Exception {
//        // Arrange
//
//
//        // Act
//        new WebSecurityConfig(mockedJwtAuthenticationFilter, mockedAuthenticationProvider, mockedLogoutHandler).securityFilterChain(mockedHttpSecurity);
//
//        // Assert
//        verify(mockedHttpSecurity).cors(any());
//        verify(mockedHttpSecurity).csrf(mockedCsrfCustomizer);
//        verify(mockedHttpSecurity).authorizeHttpRequests(any());
//        verify(mockedHttpSecurity).sessionManagement(any());
//        verify(mockedHttpSecurity).authenticationProvider(any());
//        verify(mockedHttpSecurity).addFilterBefore(any(), eq(UsernamePasswordAuthenticationFilter.class));
//        verify(mockedHttpSecurity).logout(any());
//        verifyNoMoreInteractions(mockedHttpSecurity);
//
//    }
//
//}
