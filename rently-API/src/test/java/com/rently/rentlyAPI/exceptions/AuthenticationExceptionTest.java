package com.rently.rentlyAPI.exceptions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class AuthenticationExceptionTest {


    @Test
    public void testAuthenticationException() {
        // Arrange
        AuthenticationException testAuthenticationException = new AuthenticationException("message");

        // Act

        // Assert
        assertEquals("message", testAuthenticationException.getMessage());
    }
}
