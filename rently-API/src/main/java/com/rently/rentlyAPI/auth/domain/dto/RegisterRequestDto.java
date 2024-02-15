package com.rently.rentlyAPI.auth.domain.dto;


import com.rently.rentlyAPI.auth.domain.enums.Provider;
import com.rently.rentlyAPI.auth.domain.enums.Role;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class RegisterRequestDto {

  private String firstname;
  
  private String lastname;
  
  private String email;
  
  private String password;
  
  @Builder.Default
  private Role role = Role.USER;
  
  @Builder.Default
  private Provider provider = Provider.RENTLY;
}