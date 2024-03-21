package com.rently.rentlyAPI.auth.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class ChangePasswordRequestDto {

    @NotBlank(message = "The current password is required")
    @JsonProperty("old_password")
    private String oldPassword;
    
    @NotBlank(message = "The new password is required")
    @JsonProperty("new_password")
    private String newPassword;
    
    @NotBlank(message = "The confirmation password is required")
    @JsonProperty("confirm_password")
    private String confirmationPassword;
}
