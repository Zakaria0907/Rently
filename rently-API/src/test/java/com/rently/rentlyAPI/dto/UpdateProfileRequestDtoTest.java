package com.rently.rentlyAPI.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UpdateProfileRequestDtoTest {

    @Test
    public void testArgsConstructor() {
        // Arrange
        UpdateProfileRequestDto testUpdateProfileRequestDto = new UpdateProfileRequestDto();
        testUpdateProfileRequestDto.setFirstname("Bing");
        testUpdateProfileRequestDto.setLastname("Bong");
        testUpdateProfileRequestDto.setPhoneNumber("(123) 456-7890");
        testUpdateProfileRequestDto.setBio("biography");

        // Act

        // Assert
        assertEquals("Bing", testUpdateProfileRequestDto.getFirstname());
        assertEquals("Bong", testUpdateProfileRequestDto.getLastname());
        assertEquals("(123) 456-7890", testUpdateProfileRequestDto.getPhoneNumber());
        assertEquals("biography", testUpdateProfileRequestDto.getBio());
    }


}
