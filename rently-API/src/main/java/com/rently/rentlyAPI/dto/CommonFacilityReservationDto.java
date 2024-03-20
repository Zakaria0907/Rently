package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.CommonFacilityReservation;
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
    private Integer companyId;

    @JsonProperty("common_facility_id")
    @NotBlank(message = "The common facility id is required")
    private Integer commonFacilityId;

    @JsonProperty("occupant_id")
    @NotBlank(message = "The occupant id is required")
    private Integer occupantId;

    @JsonProperty("reservation_date")
    @NotBlank(message = "The date is required")
    private String reservationDate;


    public static CommonFacilityReservation toEntity(CommonFacilityReservationDto commonFacilityReservationDto) {
        return com.rently.rentlyAPI.entity.CommonFacilityReservation.builder()
                .date(commonFacilityReservationDto.getReservationDate())
                //TODO: handle logic for setting the companyId, commonFacilityId, and occupantId
                //The companyId, commonFacilityId, and occupantId are set in the business logic
                //.company(commonFacilityReservationDto.getCompany())
                //.commonFacility(commonFacilityReservationDto.getCommonFacility())
                //.occupant(commonFacilityReservationDto.getOccupant())
                .build();

    }

    public static CommonFacilityReservationDto fromEntity(CommonFacilityReservation commonFacilityReservation){
        return CommonFacilityReservationDto.builder()
                .companyId(commonFacilityReservation.getCompany().getId())
                .commonFacilityId(commonFacilityReservation.getCommonFacility().getId())
                .occupantId(commonFacilityReservation.getOccupant().getId())
                .reservationDate(commonFacilityReservation.getDate())
                .build();
    }
}


