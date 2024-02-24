package com.rently.rentlyAPI.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChangePasswordRequestDto {

    @NotNull(message = "The current password is required")
    @NotBlank(message = "The current password is required")
    @NotEmpty(message = "The current password is required")
    private String currentPassword;

    @NotNull(message = "The new password is required")
    @NotBlank(message = "The new password is required")
    @NotEmpty(message = "The new password is required")
    private String newPassword;

    @NotNull(message = "The confirmation password is required")
    @NotBlank(message = "The confirmation password is required")
    @NotEmpty(message = "The confirmation password is required")
    private String confirmationPassword;
}
