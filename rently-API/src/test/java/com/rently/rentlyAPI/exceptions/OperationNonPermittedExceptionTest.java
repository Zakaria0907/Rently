package com.rently.rentlyAPI.exceptions;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class OperationNonPermittedExceptionTest {

    @Test
    public void testConstructorAndGetters() {
        // Arrange
        OperationNonPermittedException testOperationNonPermittedException = new OperationNonPermittedException("errorMsg");

        // Act

        // Assert
        assertEquals("errorMsg", testOperationNonPermittedException.getErrorMsg());

    }

}
