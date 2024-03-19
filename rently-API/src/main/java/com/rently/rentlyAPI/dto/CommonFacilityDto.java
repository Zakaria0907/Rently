package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.CommonFacility;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class CommonFacilityDto {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("facility_name")
    private String facilityName;
    @JsonProperty("description")
    private String description;
    @JsonProperty("building_id")
    private Integer buildingId;

    public static CommonFacilityDto fromEntity(com.rently.rentlyAPI.entity.CommonFacility commonFacility) {
        return CommonFacilityDto.builder()
                .id(commonFacility.getId())
                .facilityName(commonFacility.getName())
                .description(commonFacility.getDescription())
                .buildingId(commonFacility.getBuilding().getId())
                .build();
    }

    public static CommonFacility toEntity(CommonFacilityDto commonFacilityDto) {
        return com.rently.rentlyAPI.entity.CommonFacility.builder()
                .id(commonFacilityDto.getId())
                .name(commonFacilityDto.getFacilityName())
                .description(commonFacilityDto.getDescription())
                .building(Building.builder().id(commonFacilityDto.getBuildingId()).build())
                .build();
    }
}
