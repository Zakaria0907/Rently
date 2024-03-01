package com.rently.rentlyAPI.security.utils;

import io.jsonwebtoken.Claims;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

public class JwtUtilsTest {

    UserDetails mockedUserDetails = mock(UserDetails.class);
    Collection mockedCollectionAuthorities = mock(Collection.class);
    Stream mockedStreamAuthorities = mock(Stream.class);
    Stream<String> mockedStreamString = mock(Stream.class);
    HashMap<String, Object> mockedHashMap = mock(HashMap.class);

    @Test
    public void testGenerateTokenOneParam() {
        // Arrange
        JwtUtils testJwtUtils = Mockito.spy(new JwtUtils());
        doReturn("Hello").when(testJwtUtils).generateToken(new HashMap<>(), mockedUserDetails);

        // Act
        String testGenerateToken = testJwtUtils.generateToken(mockedUserDetails);

        // Assert
        assertEquals("Hello", testGenerateToken);
    }


    @Test
    public void testGenerateTokenTwoParam() {
        // Arrange
        JwtUtils testJwtUtils = new JwtUtils();
        when(mockedUserDetails.getAuthorities()).thenReturn(mockedCollectionAuthorities);
        when(mockedCollectionAuthorities.stream()).thenReturn(mockedStreamAuthorities);
        when(mockedStreamString.map(any(Function.class))).thenReturn(mockedStreamString);
        when(mockedUserDetails.getUsername()).thenReturn("bingbong@gmail.com");

        // Act
        String testGenerateToken = testJwtUtils.generateToken(new HashMap<>(), mockedUserDetails);

        // Assert
        assertFalse(testGenerateToken.isEmpty());
    }

    @Test
    public void testGenerateRefreshToken() {
        // Arrange
        JwtUtils testJwtUtils = new JwtUtils();

        // Act
        String testGenerateToken = testJwtUtils.generateRefreshToken(mockedUserDetails);

        // Assert
        assertFalse(testGenerateToken.isEmpty());
    }


}
