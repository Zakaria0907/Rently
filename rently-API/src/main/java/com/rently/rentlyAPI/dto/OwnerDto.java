package com.rently.rentlyAPI.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.rently.rentlyAPI.entity.user.Employee;
import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.entity.user.PublicUser;
import com.rently.rentlyAPI.security.Role;
import jakarta.persistence.Column;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@AllArgsConstructor
public class OwnerDto extends RootUserDto {
	
	@JsonProperty("id")
	private Integer id;
	
	// number of property owned
	@JsonProperty("property_owned")
	private int propertyOwned;
	
	// number of request asked
	@JsonProperty("request_count")
	private int requestCount;
	
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
	
	public static Owner toEntity(OwnerDto ownerDto){
		return Owner.builder()
				.email(ownerDto.getEmail())
				.password(ownerDto.getPassword())
				.firstName(ownerDto.getFirstName())
				.lastName(ownerDto.getLastName())
				.phoneNumber(ownerDto.getPhoneNumber())
				.bio(ownerDto.getBio())
				.role(Role.OWNER)
				.propertyOwned(0)
				.requestCount(0)
				.build();
		
	}
	
	public static OwnerDto fromEntity(Owner owner){
		return OwnerDto.builder()
				.email(owner.getEmail())
				.password(owner.getPassword())
				.firstName(owner.getFirstName())
				.lastName(owner.getLastName())
				.phoneNumber(owner.getPhoneNumber())
				.bio(owner.getBio())
				.role(owner.getRole().name())
				.propertyOwned(owner.getPropertyOwned())
				.requestCount(owner.getRequestCount())
				.build();
		
	}
	
	public static Owner fromPublicUser(PublicUser publicUser){
		return Owner.builder()
				.email(publicUser.getEmail())
				.password(publicUser.getPassword())
				.firstName(publicUser.getFirstName())
				.lastName(publicUser.getLastName())
				.phoneNumber(publicUser.getPhoneNumber())
				.bio(publicUser.getBio())
				.role(Role.OWNER)
				.propertyOwned(0)
				.requestCount(0)
				.build();
	}
}
