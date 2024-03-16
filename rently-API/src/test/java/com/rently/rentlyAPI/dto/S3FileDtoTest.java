package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.S3File;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class S3FileDtoTest {


    S3File mockedS3File = mock(S3File.class);
    S3FileDto mockedS3FileDto = mock(S3FileDto.class);
    @Test
    public void testFromDTO() {
        // Arrange


        // Act
        S3File testS3File = S3FileDto.fromDTO(mockedS3FileDto);

        // Assert
        assertEquals(mockedS3FileDto.getId(), testS3File.getId());
        assertEquals(mockedS3FileDto.getDescription(), testS3File.getDescription());
        assertEquals(mockedS3FileDto.getFilename(), testS3File.getFilename());
        assertEquals(mockedS3FileDto.getFileType(), testS3File.getFileType());
        assertEquals(mockedS3FileDto.getStoredUrl(), testS3File.getStoredUrl());
    }

    @Test
    public void testToDTO() {
        // Arrange


        // Act
        S3FileDto testS3FileDto = S3FileDto.toDTO(mockedS3File);

        // Assert
        assertEquals(mockedS3File.getId(), testS3FileDto.getId());
        assertEquals(mockedS3File.getDescription(), testS3FileDto.getDescription());
        assertEquals(mockedS3File.getFilename(), testS3FileDto.getFilename());
        assertEquals(mockedS3File.getFileType(), testS3FileDto.getFileType());
        assertEquals(mockedS3File.getStoredUrl(), testS3FileDto.getStoredUrl());
    }
    
}
