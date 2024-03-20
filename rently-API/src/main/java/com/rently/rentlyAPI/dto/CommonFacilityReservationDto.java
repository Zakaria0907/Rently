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


    @JsonProperty("common_facility_id")
    @NotBlank(message = "The common facility id is required")
    private Integer commonFacilityId;


    @JsonProperty("reservation_date DD-MM-YYYY")
    @NotBlank(message = "The date is required")
    private String reservationDate;


    public static CommonFacilityReservation toEntity(CommonFacilityReservationDto commonFacilityReservationDto) {
        return com.rently.rentlyAPI.entity.CommonFacilityReservation.builder()
                .date(commonFacilityReservationDto.getReservationDate())
                //TODO: handle logic for setting the companyId, commonFacilityId, and occupantId
                .build();

    }

    public static CommonFacilityReservationDto fromEntity(CommonFacilityReservation commonFacilityReservation) {
        return CommonFacilityReservationDto.builder()
                .commonFacilityId(commonFacilityReservation.getCommonFacility().getId())
                .reservationDate(commonFacilityReservation.getDate())
                .build();
    }
}


