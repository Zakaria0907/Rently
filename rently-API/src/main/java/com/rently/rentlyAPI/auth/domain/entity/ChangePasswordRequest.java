package com.rently.rentlyAPI.auth.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChangePasswordRequest {

    private String currentPassword;
    private String newPassword;
    private String confirmationPassword;
}
