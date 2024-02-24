package com.rently.rentlyAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
@AllArgsConstructor
public class ChangePasswordRequestDto {

    private String currentPassword;
    
    private String newPassword;
    
    private String confirmationPassword;
}
