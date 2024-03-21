package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.entity.user.Renter;
import com.rently.rentlyAPI.security.Role;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class RenterDto extends RootUserDto {
	
	@JsonProperty("id")
	private Integer id;
	
	// number of property rented
	@JsonProperty("property_owned")
	private int propertyRented;
	
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
	@Builder.Default
	private String role;
	
	public static Renter toEntity(RenterDto renterDto) {
		return Renter.builder()
				.email(renterDto.getEmail())
				.password(renterDto.getPassword())
				.firstName(renterDto.getFirstName())
				.lastName(renterDto.getLastName())
				.phoneNumber(renterDto.getPhoneNumber())
				.bio(renterDto.getBio())
				.role(Role.RENTER)
				.propertyRented(renterDto.getPropertyRented())
				.build();
	}
	
	public static RenterDto fromEntity(Renter renter) {
		return RenterDto.builder()
				.email(renter.getEmail())
				.password(renter.getPassword())
				.firstName(renter.getFirstName())
				.lastName(renter.getLastName())
				.phoneNumber(renter.getPhoneNumber())
				.bio(renter.getBio())
				.role(renter.getRole().name())
				.propertyRented(renter.getPropertyRented())
				.build();
	}
	
	public static Renter fromPublicUser(PublicUser publicUser) {
		return Renter.builder()
				.email(publicUser.getEmail())
				.password(publicUser.getPassword())
				.firstName(publicUser.getFirstName())
				.lastName(publicUser.getLastName())
				.phoneNumber(publicUser.getPhoneNumber())
				.bio(publicUser.getBio())
				.role(Role.RENTER)
				.propertyRented(0)
				.build();
	}
	
}
