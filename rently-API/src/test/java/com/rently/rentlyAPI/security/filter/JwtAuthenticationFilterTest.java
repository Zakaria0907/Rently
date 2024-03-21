package com.rently.rentlyAPI.security.filter;

import com.rently.rentlyAPI.auth.entity.AccessToken;
import com.rently.rentlyAPI.auth.repository.TokenRepository;
import com.rently.rentlyAPI.utils.JwtUtils;
import jakarta.servlet.*;
import jakarta.servlet.http.*;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;

import java.io.IOException;
import java.util.Collection;
import java.util.Collections;
import java.util.Optional;
import java.util.function.Function;

import static org.mockito.Mockito.*;

public class JwtAuthenticationFilterTest {

    JwtUtils mockedJwtUtils = mock(JwtUtils.class);
    UserDetailsService mockedUserDetailsService = mock(UserDetailsService.class);
    TokenRepository mockedTokenRepository = mock(TokenRepository.class);
    HttpServletRequest mockedHttpServletRequest = mock(HttpServletRequest.class);
    HttpServletResponse mockedHttpServletResponse = mock(HttpServletResponse.class);
    FilterChain mockedFilterChain = mock(FilterChain.class);
    UserDetails mockedUserDetails = mock(UserDetails.class);
    Optional<AccessToken> mockedOptionalAccessToken = mock(Optional.class);
    Collection<GrantedAuthority> mockedCollectionGrantedAuthority = mock(Collection.class);
    Optional<Boolean> mockedOptionalBoolean = mock(Optional.class);

    @Test
    public void testDoFilterInternalFirstDoFilterHit() throws ServletException, IOException {
        // Arrange
        JwtAuthenticationFilter testJwtAuthenticationFilter = new JwtAuthenticationFilter(mockedJwtUtils, mockedUserDetailsService, mockedTokenRepository);
        when(mockedHttpServletRequest.getServletPath()).thenReturn("/api/v1/auth");

        // Act
        testJwtAuthenticationFilter.doFilterInternal(mockedHttpServletRequest, mockedHttpServletResponse, mockedFilterChain);

        // Assert
        verify(mockedFilterChain).doFilter(mockedHttpServletRequest, mockedHttpServletResponse);

    }

    @Test
    public void testDoFilterInternalSecondDoFilterHit() throws ServletException, IOException {
        // Arrange
        JwtAuthenticationFilter testJwtAuthenticationFilter = new JwtAuthenticationFilter(mockedJwtUtils, mockedUserDetailsService, mockedTokenRepository);
        when(mockedHttpServletRequest.getServletPath()).thenReturn("no");
        when(mockedHttpServletRequest.getHeader("Authorization")).thenReturn("Authorization");

        // Act
        testJwtAuthenticationFilter.doFilterInternal(mockedHttpServletRequest, mockedHttpServletResponse, mockedFilterChain);

        // Assert
        verify(mockedFilterChain).doFilter(mockedHttpServletRequest, mockedHttpServletResponse);

    }

    @Test
    public void testDoFilterInternalUserDetailsHit() throws ServletException, IOException {
        // Arrange
        JwtAuthenticationFilter testJwtAuthenticationFilter = new JwtAuthenticationFilter(mockedJwtUtils, mockedUserDetailsService, mockedTokenRepository);
        when(mockedHttpServletRequest.getServletPath()).thenReturn("no");
        when(mockedHttpServletRequest.getHeader("Authorization")).thenReturn("Bearer ");
        when(mockedJwtUtils.extractUsername("")).thenReturn("bingbong@gmail.com");
        when(mockedUserDetailsService.loadUserByUsername("bingbong@gmail.com")).thenReturn(mockedUserDetails);
        when(mockedJwtUtils.isTokenValid("", mockedUserDetails)).thenReturn(true);
        when(mockedTokenRepository.findByToken("")).thenReturn(mockedOptionalAccessToken);
        when(mockedOptionalAccessToken.map(any(Function.class))).thenReturn(mockedOptionalBoolean);
        when(mockedOptionalBoolean.orElse(false)).thenReturn(true);
        // when(mockedOptionalAccessToken.map(t -> !t.isExpired() && !t.isRevoked())).thenReturn(mockedOptionalBoolean);
        when(mockedUserDetails.getAuthorities()).thenReturn(Collections.emptySet());

        // Act
        testJwtAuthenticationFilter.doFilterInternal(mockedHttpServletRequest, mockedHttpServletResponse, mockedFilterChain);

        // Assert
        verify(mockedFilterChain).doFilter(mockedHttpServletRequest, mockedHttpServletResponse);

    }

}
