package com.rently.rentlyAPI.dto.auth;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationRequestDto {

  @NotNull(message = "The email is required")
  @NotEmpty(message = "The email is required")
  @NotBlank(message = "The email is required")
  private String email;
  
  String password;
}
