package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.*;

@Data
@Builder
@AllArgsConstructor
public class UserDto {
	
	@JsonProperty("id")
	private Integer id;
	
	@JsonProperty("email")
	@NotBlank(message = "The email is required")
	private String email;
	
	@JsonProperty("password")
	@NotBlank(message = "The password name is required")
	private String password;
	
	@JsonProperty("first_name")
	@NotBlank(message = "The first name is required")
	private String firstName;
	
	@JsonProperty("last_name")
	@NotBlank(message = "The last name is required")
	private String lastName;
	
	@JsonProperty("phone_number")
	private String phoneNumber;
	
	@JsonProperty("bio")
	private String bio;
	
	@JsonProperty("role")
	private String role;
	
}
