package com.rently.rentlyAPI.handlers;

import org.junit.jupiter.api.Test;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class ExceptionRepresentationTest {

    Set<String> mockedSet = mock(Set.class);
    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        ExceptionRepresentation testExceptionRepresentation = new ExceptionRepresentation("errorMsg", "errorSrc", mockedSet);

        // Assert
        assertEquals("errorMsg", testExceptionRepresentation.getErrorMessage());
        assertEquals("errorSrc", testExceptionRepresentation.getErrorSource());
        assertEquals(mockedSet, testExceptionRepresentation.getValidationErrors());
    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        ExceptionRepresentation testExceptionRepresentation = ExceptionRepresentation.builder().build();

        // Assert
        assertNull(testExceptionRepresentation.getErrorMessage());
        assertNull(testExceptionRepresentation.getErrorSource());
        assertNull(testExceptionRepresentation.getValidationErrors());
    }

}
