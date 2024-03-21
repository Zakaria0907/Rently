package com.rently.rentlyAPI.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@SuperBuilder
@AllArgsConstructor
@NoArgsConstructor
public abstract class Occupant extends User {
	
	// number of year of residence
	@Column(nullable = false, columnDefinition = "int default 0")
	private int residentSinceYears;

}
