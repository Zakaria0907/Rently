package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * This is a general DTO for sending emails, it converts a JSON from the request body into a send-able Email Java object
 */
@Data
@Builder
@AllArgsConstructor
public class EmailDto {
	
	@JsonProperty("to")
	@Email(message = "Invalid email address")
	@NotBlank(message = "Recipient email is required")
	private String to;
	
	@JsonProperty("cc")
	@Email(message = "Invalid email address")
	private List<String> cc;
	
	@JsonProperty("subject")
	@NotBlank(message = "Email subject is required")
	private String subject;
	
	@JsonProperty("body")
	@NotNull(message = "Email body is required")
	private String body;

}
