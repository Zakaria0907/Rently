package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class OwnerDtoTest {

    @Test
    public void testToEntity() {
        OwnerDto ownerDto = OwnerDto.builder()
                .email("test@example.com")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .bio("Test bio")
                .role(Role.RENTER.name())
                .propertyOwned(2)
                .build();

        Owner owner = OwnerDto.toEntity(ownerDto);

        assertEquals(ownerDto.getEmail(), owner.getEmail());
        assertEquals(ownerDto.getPassword(), owner.getPassword());
        assertEquals(ownerDto.getFirstName(), owner.getFirstName());
        assertEquals(ownerDto.getLastName(), owner.getLastName());
        assertEquals(ownerDto.getPhoneNumber(), owner.getPhoneNumber());
        assertEquals(ownerDto.getBio(), owner.getBio());
        assertEquals(Role.OWNER, owner.getRole());
    }

    @Test
    public void testFromEntity() {
        Owner owner = new Owner();
        owner.setId(1);
        owner.setEmail("test@example.com");
        owner.setPassword("password123");
        owner.setFirstName("John");
        owner.setLastName("Doe");
        owner.setPhoneNumber("1234567890");
        owner.setBio("Test bio");
        owner.setRole(Role.RENTER);
        owner.setPropertyOwned(2);

        OwnerDto ownerDto = OwnerDto.fromEntity(owner);
        ownerDto.setId(owner.getId());

        assertEquals(owner.getId(), ownerDto.getId());
        assertEquals(owner.getEmail(), ownerDto.getEmail());
        assertEquals(owner.getPassword(), ownerDto.getPassword());
        assertEquals(owner.getFirstName(), ownerDto.getFirstName());
        assertEquals(owner.getLastName(), ownerDto.getLastName());
        assertEquals(owner.getPhoneNumber(), ownerDto.getPhoneNumber());
        assertEquals(owner.getBio(), ownerDto.getBio());
        assertEquals(owner.getRole().name(), ownerDto.getRole());
        assertEquals(owner.getPropertyOwned(), ownerDto.getPropertyOwned());
    }
}
