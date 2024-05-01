package com.rently.rentlyAPI.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RegistrationKeyRequestDtoTest {

    @Test
    void testAllArgsConstructor() {
        RegistrationKeyRequestDto dto = new RegistrationKeyRequestDto(1, 2, "admin");

        assertEquals(1, dto.getBuildingId());
        assertEquals(2, dto.getCondoId());
        assertEquals("admin", dto.getRole());
    }
}
