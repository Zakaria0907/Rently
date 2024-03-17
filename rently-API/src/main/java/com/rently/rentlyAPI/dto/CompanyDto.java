package com.rently.rentlyAPI.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.CompanyAdmin;
import com.rently.rentlyAPI.security.Role;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CompanyDto {

    @JsonProperty("id")
    private Integer id;

    @JsonProperty("company_name")
    @NotBlank(message = "The company name is required")
    private String name;

    public Company toEntity(CompanyDto companyDto){
        return Company.builder()
                .name(companyDto.getName())
                .build();
    }

    public CompanyDto fromEntity(Company company){
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }
}
