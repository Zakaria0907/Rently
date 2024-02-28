package com.rently.rentlyAPI.exceptions;

import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import static org.mockito.Mockito.mock;

public class AuthenticationExceptionTest {

    @Test
    public void testAuthenticationException() {
        // Arrange


        // Act
        AuthenticationException mockedAuthenticationException = mock(AuthenticationException.class);

        // Assert
        Mockito.verify(mockedAuthenticationException);

    }
}
