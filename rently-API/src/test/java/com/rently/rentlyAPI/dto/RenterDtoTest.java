package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.user.Renter;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

//@SpringBootTest
public class RenterDtoTest {

    @Test
    public void testToEntity() {
        RenterDto renterDto = RenterDto.builder()
                .email("test@example.com")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .bio("Test bio")
                .role(Role.RENTER.name())
                .propertyRented(2)
                .build();

        Renter renter = RenterDto.toEntity(renterDto);

        assertEquals(renterDto.getEmail(), renter.getEmail());
        assertEquals(renterDto.getPassword(), renter.getPassword());
        assertEquals(renterDto.getFirstName(), renter.getFirstName());
        assertEquals(renterDto.getLastName(), renter.getLastName());
        assertEquals(renterDto.getPhoneNumber(), renter.getPhoneNumber());
        assertEquals(renterDto.getBio(), renter.getBio());
        assertEquals(Role.RENTER, renter.getRole());
        assertEquals(renterDto.getPropertyRented(), renter.getPropertyRented());
    }

    @Test
    public void testFromEntity() {
        Renter renter = new Renter();
        renter.setId(1);
        renter.setEmail("test@example.com");
        renter.setPassword("password123");
        renter.setFirstName("John");
        renter.setLastName("Doe");
        renter.setPhoneNumber("1234567890");
        renter.setBio("Test bio");
        renter.setRole(Role.RENTER);
        renter.setPropertyRented(2);

        RenterDto renterDto = RenterDto.fromEntity(renter);
        renterDto.setId(renter.getId());

        assertEquals(renter.getId(), renterDto.getId());
        assertEquals(renter.getEmail(), renterDto.getEmail());
        assertEquals(renter.getPassword(), renterDto.getPassword());
        assertEquals(renter.getFirstName(), renterDto.getFirstName());
        assertEquals(renter.getLastName(), renterDto.getLastName());
        assertEquals(renter.getPhoneNumber(), renterDto.getPhoneNumber());
        assertEquals(renter.getBio(), renterDto.getBio());
        assertEquals(renter.getRole().name(), renterDto.getRole());
        assertEquals(renter.getPropertyRented(), renterDto.getPropertyRented());
    }
}
