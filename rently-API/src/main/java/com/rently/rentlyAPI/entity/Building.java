package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.Request;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;
import java.util.Set;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "building")
public class Building extends AbstractEntity {
	
	@NotBlank(message = "Name is required")
	private String name;
	
	@NotBlank(message = "Address is required")
	private String address;

	private String description;
	
	// This column links to the Condo entities
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Condo> condos;

	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Locker> buildingLockers;

	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Parking> buildingParkings;
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<CommonFacility> commonFacilities;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "company_id")
	private Company company;




}
