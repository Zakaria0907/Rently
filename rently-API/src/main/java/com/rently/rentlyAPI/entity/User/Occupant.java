package com.rently.rentlyAPI.entity.User;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "occupant_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Occupant extends User {
	
	// number of year of residence
	@Column(nullable = false, columnDefinition = "int default 0")
	private int residentSinceYears;

}
