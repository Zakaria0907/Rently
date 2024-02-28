package com.rently.rentlyAPI.exceptions;

import com.amazonaws.SdkClientException;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;

public class FileUploadExceptionTest {

    FileUploadException mockedFileUploadException = mock(FileUploadException.class);
    IOException mockedIOException = mock(IOException.class);
    SdkClientException mockedSdkClientException = mock(SdkClientException.class);
    IllegalArgumentException mockedIllegalArgumentException = mock(IllegalArgumentException.class);

    @Test
    public void testFileUploadException() {
        // Arrange


        // Act

        // Assert
        Mockito.verify(mockedFileUploadException);

    }

    @Test
    public void testWrapIOException() {
        // Arrange


        // Act
        FileUploadException testFileUploadException = FileUploadException.wrap(mockedIOException);

        // Assert
        assertEquals(mockedIOException.getMessage(), testFileUploadException.getMessage());

    }

    @Test
    public void testWrapSdkClientException() {
        // Arrange


        // Act
        FileUploadException testFileUploadException = FileUploadException.wrap(mockedSdkClientException);

        // Assert
        assertEquals(mockedSdkClientException.getMessage(), testFileUploadException.getMessage());

    }


}
