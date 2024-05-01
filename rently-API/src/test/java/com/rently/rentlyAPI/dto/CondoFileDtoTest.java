package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.CondoFile;
import com.rently.rentlyAPI.entity.enums.FileType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

public class CondoFileDtoTest {

    @Test
    void testToEntity() {
        CondoFileDto condoFileDto = CondoFileDto.builder()
                .filename("file.pdf")
                .fileType("PDF")
                .description("Description")
                .build();

        CondoFile condoFile = CondoFileDto.toEntity(condoFileDto);

        assertNotNull(condoFile);
        assertEquals("file.pdf", condoFile.getFilename());
        assertEquals(FileType.PDF, condoFile.getFileType());
        assertEquals("Description", condoFile.getDescription());
    }

    @Test
    void testFromEntity() {
        Condo condo = new Condo();
        condo.setId(1);

        CondoFile condoFile = CondoFile.builder()
                .id(1)
                .filename("file.pdf")
                .fileType(FileType.PDF)
                .storedUrl("stored_url")
                .description("Description")
                .condo(condo)
                .build();

        CondoFileDto condoFileDto = CondoFileDto.fromEntity(condoFile);

        assertNotNull(condoFileDto);
        assertEquals(1, condoFileDto.getId());
        assertEquals("file.pdf", condoFileDto.getFilename());
        assertEquals("PDF", condoFileDto.getFileType());
        assertEquals("stored_url", condoFileDto.getStoredUrl());
        assertEquals("Description", condoFileDto.getDescription());
        assertEquals(1, condoFileDto.getCondoId());
    }
}
