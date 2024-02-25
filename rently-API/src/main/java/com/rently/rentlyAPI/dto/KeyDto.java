package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Key;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class KeyDto {

    @NotNull
    public String key;

    public boolean revoked;

    public boolean isActive;

    public Integer companyId;

    public Integer userId;

    public Role role;

    public static KeyDto fromEntity(Key key) {
        return KeyDto.builder()
                .key(key.getKey())
                .revoked(key.isRevoked())
                .isActive(key.isActive())
                .companyId(key.getCompanyId())
                .userId(key.getUser().getId())
                .role(key.getRole())
                .build();
    }

    public static Key toEntity(KeyDto dto) {
        return Key.builder()
                .key(dto.getKey())
                .revoked(dto.isRevoked())
                .isActive(dto.isActive())
                .companyId(dto.getCompanyId())
                .role(dto.getRole())
                .build();
    }

}
