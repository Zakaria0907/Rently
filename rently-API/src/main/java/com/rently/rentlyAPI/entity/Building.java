package com.rently.rentlyAPI.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

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
	
	@Column(nullable = false, columnDefinition = "int default 0")
	private Integer unitCount;
	
	private String description;
	
	// This column links to the Condo entities
	@OneToMany(mappedBy = "building", cascade = CascadeType.ALL, orphanRemoval = true)
	private List<Condo> condos;
	
	// This column links to the User entity acting as a company
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "user_id")
	private User company;
}
