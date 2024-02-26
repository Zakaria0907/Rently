package com.rently.rentlyAPI.dto;

import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

@Data
public class UpdateProfileRequestDto {
	private String firstname;
	private String lastname;
	private String phoneNumber;
	private String bio;
}
