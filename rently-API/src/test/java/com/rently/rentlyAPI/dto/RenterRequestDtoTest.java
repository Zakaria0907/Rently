package com.rently.rentlyAPI.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class RenterRequestDtoTest {

    @Test
    public void testArgsConstructor() {
        // Arrange
        RenterRequestDto renterRequestDto = new RenterRequestDto(1,2);

        // Act

        // Assert
        assertEquals(1, renterRequestDto.getRenterId());
        assertEquals(2, renterRequestDto.getCondoId());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange
        RenterRequestDto renterRequestDto = new RenterRequestDto();

        // Act

        // Assert
        assertNull(renterRequestDto.getRenterId());
        assertNull(renterRequestDto.getCondoId());
    }

    @Test
    public void testBuilder() {
        // Arrange
        RenterRequestDto renterRequestDto = RenterRequestDto.builder().build();

        // Act

        // Assert
        assertNull(renterRequestDto.getRenterId());
        assertNull(renterRequestDto.getCondoId());
    }
}
