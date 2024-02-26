package com.rently.rentlyAPI.dto;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@Builder
public class UserProfileDto {
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String bio;
	private String email;
	private String profilePicture;
	private String role;
	
}
