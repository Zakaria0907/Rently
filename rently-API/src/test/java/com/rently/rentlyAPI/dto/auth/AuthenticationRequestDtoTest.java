package com.rently.rentlyAPI.dto.auth;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthenticationRequestDtoTest {

    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        AuthenticationRequestDto testAuthenticationRequestDto = new AuthenticationRequestDto("bingbong@gmail.com", "password");

        // Assert
        assertEquals("bingbong@gmail.com", testAuthenticationRequestDto.getEmail());
        assertEquals("password", testAuthenticationRequestDto.getPassword());

    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        AuthenticationRequestDto testAuthenticationRequestDto = new AuthenticationRequestDto();

        // Assert
        assertNull(testAuthenticationRequestDto.getEmail());
        assertNull(testAuthenticationRequestDto.getPassword());

    }

    @Test
    public void testBuilderConstructor() {
        // Arrange


        // Act
        AuthenticationRequestDto testAuthenticationRequestDto = AuthenticationRequestDto.builder().build();

        // Assert
        assertNull(testAuthenticationRequestDto.getEmail());
        assertNull(testAuthenticationRequestDto.getPassword());

    }

}
