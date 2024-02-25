package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Condo;
import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class CondoDto {

    private Integer id;

    private String name;

    private String address;

    private String condoNumber;

    private String condoType;

    private String description;

    private CondoStatus status;

    private Integer ownerId;

    private Integer tenantId;

    public static CondoDto fromEntity(Condo condo) {
        return CondoDto.builder()
                .id(condo.getId())
                .name(condo.getName())
                .address(condo.getAddress())
                .condoNumber(condo.getCondoNumber())
                .condoType(condo.getCondoType())
                .description(condo.getDescription())
                .status(condo.getStatus())
                .ownerId(condo.getOwnerId().getId())
                .tenantId(condo.getTenantId().getId())
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
                .ownerId(User.builder().id(condoDto.getOwnerId()).build())
                .tenantId(User.builder().id(condoDto.getTenantId()).build())
                .build();
    }

}
