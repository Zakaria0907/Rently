package com.rently.rentlyAPI.exceptions;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class ObjectValidationExceptionTest {

    Set<String> mockedSet = mock(Set.class);
    @Test
    public void testGettersAndSetters() {
        // Arrange
        ObjectValidationException testObjectValidationException = new ObjectValidationException(mockedSet, "Hello");

        // Act

        
        // Assert
        assertEquals(mockedSet, testObjectValidationException.getViolations());
        assertEquals("Hello", testObjectValidationException.getViolationSource());
    }

}
