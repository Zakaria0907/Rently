package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.CommonFacility;
import com.rently.rentlyAPI.entity.CommonFacilityReservation;
import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.user.Occupant;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;


@Data
@Builder
@AllArgsConstructor
public class CommonFacilityReservationDto {

    @JsonProperty("company_id")
    @NotBlank(message = "The company id is required")
    private Company company;

    @JsonProperty("common_facility_id")
    @NotBlank(message = "The email is required")
    private CommonFacility commonFacility;

    @JsonProperty("occupant_id")
    @NotBlank(message = "The occupant id is required")
    private Occupant occupant;

    public static CommonFacilityReservation toEntity(CommonFacilityReservationDto commonFacilityReservationDto){
        return com.rently.rentlyAPI.entity.CommonFacilityReservation.builder()
                .company(commonFacilityReservationDto.getCompany())
                .commonFacility(commonFacilityReservationDto.getCommonFacility())
                .occupant(commonFacilityReservationDto.getOccupant())
                .build();

    }

    public static CommonFacilityReservationDto fromEntity(CommonFacilityReservation commonFacilityReservation){
        return CommonFacilityReservationDto.builder()
                .company(commonFacilityReservation.getCompany())
                .commonFacility(commonFacilityReservation.getCommonFacility())
                .occupant(commonFacilityReservation.getOccupant())
                .build();
    }
}


