package com.rently.rentlyAPI.dto;


import com.rently.rentlyAPI.entity.Company;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CompanyDto {
    @Nullable
    private Integer id;

    @NotBlank(message = "Name is required")
    private String name;

    public static CompanyDto fromEntity(Company company) {
        return CompanyDto.builder()
                .id(company.getId())
                .name(company.getName())
                .build();
    }

    public static Company toEntity(CompanyDto companyDto) {
        return Company.builder()
                .id(companyDto.getId())
                .name(companyDto.getName())
                .build();
    }

}
