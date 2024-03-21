package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyAdminDtoTest {

    @Test
    public void testToEntity() {
        CompanyAdminDto companyAdminDto = CompanyAdminDto.builder()
                .email("admin@example.com")
                .password("password123")
                .firstName("John")
                .lastName("Doe")
                .phoneNumber("1234567890")
                .bio("Test bio")
                .build();

        CompanyAdmin companyAdmin = CompanyAdminDto.toEntity(companyAdminDto);

        assertEquals(companyAdminDto.getEmail(), companyAdmin.getEmail());
        assertEquals(companyAdminDto.getPassword(), companyAdmin.getPassword());
        assertEquals(companyAdminDto.getFirstName(), companyAdmin.getFirstName());
        assertEquals(companyAdminDto.getLastName(), companyAdmin.getLastName());
        assertEquals(companyAdminDto.getPhoneNumber(), companyAdmin.getPhoneNumber());
        assertEquals(companyAdminDto.getBio(), companyAdmin.getBio());
        assertEquals(Role.COMPANY_ADMIN, companyAdmin.getRole());
    }

    @Test
    public void testFromEntity() {
        CompanyAdmin companyAdmin = new CompanyAdmin();
        companyAdmin.setId(1);
        companyAdmin.setEmail("admin@example.com");
        companyAdmin.setFirstName("John");
        companyAdmin.setLastName("Doe");
        companyAdmin.setPhoneNumber("1234567890");
        companyAdmin.setBio("Test bio");
        companyAdmin.setRole(Role.COMPANY_ADMIN);

        Company company = new Company();
        company.setId(1);
        companyAdmin.setCompany(company);

        CompanyAdminDto companyAdminDto = CompanyAdminDto.fromEntity(companyAdmin);

        assertEquals(companyAdmin.getId(), companyAdminDto.getId());
        assertEquals(companyAdmin.getEmail(), companyAdminDto.getEmail());
        assertEquals(companyAdmin.getFirstName(), companyAdminDto.getFirstName());
        assertEquals(companyAdmin.getLastName(), companyAdminDto.getLastName());
        assertEquals(companyAdmin.getPhoneNumber(), companyAdminDto.getPhoneNumber());
        assertEquals(companyAdmin.getBio(), companyAdminDto.getBio());
        assertEquals(Role.COMPANY_ADMIN.name(), companyAdminDto.getRole());
    }
}
