package com.rently.rentlyAPI.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class UserProfileDtoTest {

    @Test
    public void testArgsConstructor() {
        // Arrange
        UserProfileDto testUserProfileDto = new UserProfileDto("Bing", "Bong", "(123) 456-7890", "biography"
            , "bingbong@gmail.com", "DJKhaled", "ADMIN");

        // Act

        // Assert
        assertEquals("Bing", testUserProfileDto.getFirstname());
        assertEquals("Bong", testUserProfileDto.getLastname());
        assertEquals("(123) 456-7890", testUserProfileDto.getPhoneNumber());
        assertEquals("biography", testUserProfileDto.getBio());
        assertEquals("bingbong@gmail.com", testUserProfileDto.getEmail());
        assertEquals("DJKhaled", testUserProfileDto.getProfilePicture());
        assertEquals("ADMIN", testUserProfileDto.getRole());
    }

    @Test
    public void testBuilder() {
        // Arrange
        UserProfileDto testUserProfileDto = UserProfileDto.builder().build();


        // Act


        // Assert
        assertNull(testUserProfileDto.getFirstname());
        assertNull(testUserProfileDto.getLastname());
        assertNull(testUserProfileDto.getPhoneNumber());
        assertNull(testUserProfileDto.getBio());
        assertNull(testUserProfileDto.getEmail());
        assertNull(testUserProfileDto.getProfilePicture());
        assertNull(testUserProfileDto.getRole());
    }
    
}
