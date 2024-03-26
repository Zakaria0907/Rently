package com.rently.rentlyAPI.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

//@SpringBootTest
public class UserDtoTest {

    @Test
    public void testUserDtoInitialization() {
        Integer id = 1;
        String email = "user@example.com";
        String password = "password";
        String firstName = "John";
        String lastName = "Doe";
        String phoneNumber = "1234567890";
        String bio = "User bio";
        String role = "USER";

        UserDto userDto = UserDto.builder()
                .id(id)
                .email(email)
                .password(password)
                .firstName(firstName)
                .lastName(lastName)
                .phoneNumber(phoneNumber)
                .bio(bio)
                .role(role)
                .build();

        assertNotNull(userDto);
        assertEquals(id, userDto.getId());
        assertEquals(email, userDto.getEmail());
        assertEquals(password, userDto.getPassword());
        assertEquals(firstName, userDto.getFirstName());
        assertEquals(lastName, userDto.getLastName());
        assertEquals(phoneNumber, userDto.getPhoneNumber());
        assertEquals(bio, userDto.getBio());
        assertEquals(role, userDto.getRole());
    }
}
