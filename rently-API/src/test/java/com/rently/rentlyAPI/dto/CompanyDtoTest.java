package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Company;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
public class CompanyDtoTest {

    @Test
    public void testToEntity() {
        CompanyDto companyDto = CompanyDto.builder()
                .name("Test Company")
                .build();

        Company company = CompanyDto.toEntity(companyDto);

        assertEquals(companyDto.getName(), company.getName());
    }

    @Test
    public void testFromEntity() {
        Company company = new Company();
        company.setId(1);
        company.setName("Test Company");

        CompanyDto companyDto = CompanyDto.fromEntity(company);

        assertEquals(company.getId(), companyDto.getId());
        assertEquals(company.getName(), companyDto.getName());
    }
}
