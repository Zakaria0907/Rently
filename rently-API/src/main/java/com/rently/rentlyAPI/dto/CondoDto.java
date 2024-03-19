package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.Locker;
import com.rently.rentlyAPI.entity.Parking;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class CondoDto {

    @JsonProperty("id")
    private Integer id;

    // address is building address + condo number
    @JsonProperty("address")
    private String address;

    @JsonProperty("unit_number")
    @NotBlank(message = "Unit number is required")
    private Integer unitNumber;

    @JsonProperty("description")
    private String description;

    @JsonProperty("registration_key")
    private String registrationKey;

    @JsonProperty("status")
    private String status;

    @JsonProperty("building_id")
    private Integer buildingId;

    @JsonProperty("parking_id")
    private Integer parkingId;

    @JsonProperty("locker_id")
    private Integer lockerId;


    public static Condo toEntity(CondoDto condoDto){
        return Condo.builder()
                // Address logic is handled in business logic (Building address + condo number)
                .unitNumber(condoDto.getUnitNumber())
                .description(condoDto.getDescription())
                // registration key is set in the business logic
                .status(CondoStatus.valueOf(condoDto.getStatus()))
                // building is set in the business logic
                // parking is set in the business logic
                // locker is set in the business logic
                .build();
    }

    public static CondoDto fromEntity(Condo condo) {
        return CondoDto.builder()
                .address(condo.getAddress())
                .unitNumber(condo.getUnitNumber())
                .description(condo.getDescription())
                .registrationKey(condo.getRegistrationKey())
                .status(condo.getStatus().toString())
                .buildingId(condo.getBuilding().getId())
                .parkingId(condo.getParking() != null ? condo.getParking().getId() : null)
                .lockerId(condo.getLocker() != null ? condo.getLocker().getId() : null)
                .build();
    }

}
