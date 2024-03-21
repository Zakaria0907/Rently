//package com.rently.rentlyAPI.entity;
//
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.*;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.mock;
//
//public class S3FileTest {
//
//    FileType mockedFileType = mock(FileType.class);
//    @Test
//    public void testAllArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        S3File testS3File = new S3File("S3File", "fileName", mockedFileType, "stonedUrl");
//
//        // Assert
//        assertEquals("S3File", testS3File.getDescription());
//        assertEquals("fileName", testS3File.getFilename());
//        assertEquals(mockedFileType, testS3File.getFileType());
//        assertEquals("stonedUrl", testS3File.getStoredUrl());
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        S3File testS3File = new S3File();
//
//        // Assert
//        assertNull(testS3File.getDescription());
//        assertNull(testS3File.getStoredUrl());
//        assertNull(testS3File.getFilename());
//        assertNull(testS3File.getFileType());
//    }
//
//    @Test
//    public void testSetters() {
//        // Arrange
//
//
//        // Act
//        S3File testS3File = new S3File();
//        testS3File.setDescription("description");
//        testS3File.setFilename("fileName");
//        testS3File.setFileType(mockedFileType);
//        testS3File.setStoredUrl("stonedUrl");
//
//        // Assert
//        assertEquals("description", testS3File.getDescription());
//        assertEquals("fileName", testS3File.getFilename());
//        assertEquals(mockedFileType, testS3File.getFileType());
//        assertEquals("stonedUrl", testS3File.getStoredUrl());
//    }
//
//}
