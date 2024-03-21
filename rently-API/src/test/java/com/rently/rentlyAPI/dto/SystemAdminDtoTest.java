package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class SystemAdminDtoTest {
    @Test
    public void testToEntity() {
        SystemAdminDto systemAdminDto = SystemAdminDto.builder()
                .email("admin@example.com")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .bio("Test bio")
                .build();

        SystemAdmin systemAdmin = SystemAdminDto.toEntity(systemAdminDto);

        assertEquals(systemAdminDto.getEmail(), systemAdmin.getEmail());
        assertEquals(systemAdminDto.getPassword(), systemAdmin.getPassword());
        assertEquals(systemAdminDto.getFirstName(), systemAdmin.getFirstName());
        assertEquals(systemAdminDto.getLastName(), systemAdmin.getLastName());
        assertEquals(systemAdminDto.getPhoneNumber(), systemAdmin.getPhoneNumber());
        assertEquals(systemAdminDto.getBio(), systemAdmin.getBio());
        assertEquals(Role.SYSTEM_ADMIN, systemAdmin.getRole());
    }

    @Test
    public void testFromEntity() {
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setId(1);
        systemAdmin.setEmail("admin@example.com");
        systemAdmin.setFirstName("John");
        systemAdmin.setLastName("Doe");
        systemAdmin.setPhoneNumber("1234567890");
        systemAdmin.setBio("Test bio");
        systemAdmin.setRole(Role.SYSTEM_ADMIN);

        SystemAdminDto systemAdminDto = SystemAdminDto.fromEntity(systemAdmin);

        assertEquals(systemAdmin.getId(), systemAdminDto.getId());
        assertEquals(systemAdmin.getEmail(), systemAdminDto.getEmail());
        assertEquals(systemAdmin.getFirstName(), systemAdminDto.getFirstName());
        assertEquals(systemAdmin.getLastName(), systemAdminDto.getLastName());
        assertEquals(systemAdmin.getPhoneNumber(), systemAdminDto.getPhoneNumber());
        assertEquals(systemAdmin.getBio(), systemAdminDto.getBio());
    }
}
