package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Building;
import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import jakarta.annotation.Nullable;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CondoDto {

    @Nullable
    private Integer id;


    @NotBlank(message = "Condo name is required")
    private String name;

    private String address;

    private String condoNumber;

    private String condoType;

    private String description;

    @NotNull(message = "Condo status is required")
    private CondoStatus status;

    private Integer buildingId;

    // we should add a field "floor" to the condo entity

    public static CondoDto fromEntity(Condo condo) {
        return CondoDto.builder()
                .id(condo.getId())
                .name(condo.getName())
                .address(condo.getAddress())
                .condoNumber(condo.getCondoNumber())
                .condoType(condo.getCondoType())
                .description(condo.getDescription())
                .status(condo.getStatus())
                .buildingId(condo.getBuilding().getId())
                .build();
    }

    public static Condo toEntity(CondoDto condoDto) {
        return Condo.builder()
                .id(condoDto.getId())
                .name(condoDto.getName())
                .address(condoDto.getAddress())
                .condoNumber(condoDto.getCondoNumber())
                .condoType(condoDto.getCondoType())
                .description(condoDto.getDescription())
                .status(condoDto.getStatus())
                // Building is set in the service
                .build();
    }

}
