package com.rently.rentlyAPI.auth.domain.dto;


import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.auth.domain.dto.UserDto;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AuthenticationResponseDto {

  @JsonProperty("access_token")
  private String accessToken;
  
  @JsonProperty("refresh_token")
  private String refreshToken;
  
  @JsonProperty("user")
  private UserDto user;
}
