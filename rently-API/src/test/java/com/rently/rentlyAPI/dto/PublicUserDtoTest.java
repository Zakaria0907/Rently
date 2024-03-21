package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class PublicUserDtoTest {

    @Test
    public void testFromEntity() {
        // Given
        PublicUser publicUser = PublicUser.builder()
                .id(1)
                .email("user@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .bio("User bio")
                .role(Role.PUBLIC_USER)
                .build();

        // When
        PublicUserDto publicUserDto = PublicUserDto.fromEntity(publicUser);

        // Then
        assertNotNull(publicUserDto);
        assertEquals(publicUser.getId(), publicUserDto.getId());
        assertEquals(publicUser.getEmail(), publicUserDto.getEmail());
        assertEquals(publicUser.getFirstName(), publicUserDto.getFirstName());
        assertEquals(publicUser.getLastName(), publicUserDto.getLastName());
        assertEquals(publicUser.getPhoneNumber(), publicUserDto.getPhoneNumber());
        assertEquals(publicUser.getBio(), publicUserDto.getBio());
        assertEquals(publicUser.getRole().name(), publicUserDto.getRole());
    }

    @Test
    public void testToEntity() {
        PublicUserDto publicUserDto = PublicUserDto.builder()
                .id(1)
                .email("user@example.com")
                .password("password")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .bio("User bio")
                .role(Role.PUBLIC_USER.name())
                .registrationKey("REG123")
                .build();

        PublicUser publicUser = PublicUserDto.toEntity(publicUserDto);

        assertNotNull(publicUser);
        assertEquals(publicUserDto.getEmail(), publicUser.getEmail());
        assertEquals(publicUserDto.getPassword(), publicUser.getPassword());
        assertEquals(publicUserDto.getFirstName(), publicUser.getFirstName());
        assertEquals(publicUserDto.getLastName(), publicUser.getLastName());
        assertEquals(publicUserDto.getPhoneNumber(), publicUser.getPhoneNumber());
        assertEquals(publicUserDto.getBio(), publicUser.getBio());
        assertEquals(Role.valueOf(publicUserDto.getRole()), publicUser.getRole());
    }
}
