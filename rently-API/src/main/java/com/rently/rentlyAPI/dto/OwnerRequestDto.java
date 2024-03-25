package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.OwnerRequest;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
@AllArgsConstructor
public class OwnerRequestDto {

    @JsonProperty("id")
    private Integer id;
    @JsonProperty("owner_id")
    private Integer ownerId;
    @JsonProperty("building_id")
    private Integer buildingId;
    @JsonProperty("request_description")
    private String requestDescription;

    public static OwnerRequestDto fromEntity(OwnerRequest ownerRequest) {
        return OwnerRequestDto.builder()
                .id(ownerRequest.getId())
                .ownerId(ownerRequest.getOwner().getId())
                .buildingId(ownerRequest.getBuilding().getId())
                .requestDescription(ownerRequest.getRequestDescription())
                .build();
    }

    public static OwnerRequest toEntity(OwnerRequestDto ownerRequestDto) {
        return OwnerRequest.builder()
                .id(ownerRequestDto.getId())
                .requestDescription(ownerRequestDto.getRequestDescription())
                .build();
    }

}
