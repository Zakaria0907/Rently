package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@AllArgsConstructor
@Builder
public class CompanyFacilityDto {
    @JsonProperty("id")
    private Integer id;
    @JsonProperty("facilityName")
    private String facilityName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("building_id")
    private Integer buildingId;

    static CompanyFacilityDto fromEntity(Building building) {
        return CompanyFacilityDto.builder()
                .id(building.getId())
                .facilityName(building.getName())
                .description(building.getDescription())
                .buildingId(building.getCompany().getId())
                .build();
    }

    static CommonFacility toEntity(CompanyFacilityDto companyFacilityDto) {
        return CommonFacility.builder()
                .name(companyFacilityDto.getFacilityName())
                .description(companyFacilityDto.getDescription())
                .building(Building.builder().id(companyFacilityDto.getBuildingId()).build())
                .build();
    }
}
