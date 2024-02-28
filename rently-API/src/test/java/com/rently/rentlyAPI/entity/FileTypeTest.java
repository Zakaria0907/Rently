package com.rently.rentlyAPI.entity;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class FileTypeTest {

    @Test
    public void testConstructorPDF() {
        // Arrange


        // Act
        FileType testFileType = FileType.PDF;

        // Assert
        assertEquals(FileType.PDF, testFileType);
    }

    @Test
    public void testConstructorDOC() {
        // Arrange


        // Act
        FileType testFileType = FileType.DOC;

        // Assert
        assertEquals(FileType.DOC, testFileType);
    }

    @Test
    public void testConstructorDOCX() {
        // Arrange


        // Act
        FileType testFileType = FileType.DOCX;

        // Assert
        assertEquals(FileType.DOCX, testFileType);
    }

    @Test
    public void testConstructorXLS() {
        // Arrange


        // Act
        FileType testFileType = FileType.XLS;

        // Assert
        assertEquals(FileType.XLS, testFileType);
    }

    @Test
    public void testConstructorXLSX() {
        // Arrange


        // Act
        FileType testFileType = FileType.XLSX;

        // Assert
        assertEquals(FileType.XLSX, testFileType);
    }

    @Test
    public void testConstructorPPT() {
        // Arrange


        // Act
        FileType testFileType = FileType.PPT;

        // Assert
        assertEquals(FileType.PPT, testFileType);
    }

    @Test
    public void testConstructorPPTX() {
        // Arrange


        // Act
        FileType testFileType = FileType.PPTX;

        // Assert
        assertEquals(FileType.PPTX, testFileType);
    }

    @Test
    public void testConstructorJPG() {
        // Arrange


        // Act
        FileType testFileType = FileType.JPG;

        // Assert
        assertEquals(FileType.JPG, testFileType);
    }

    @Test
    public void testConstructorPNG() {
        // Arrange


        // Act
        FileType testFileType = FileType.PNG;

        // Assert
        assertEquals(FileType.PNG, testFileType);
    }

    @Test
    public void testConstructorGIF() {
        // Arrange


        // Act
        FileType testFileType = FileType.GIF;

        // Assert
        assertEquals(FileType.GIF, testFileType);
    }
}
