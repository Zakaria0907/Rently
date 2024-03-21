package com.rently.rentlyAPI.entity.user;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "renter")
@Data
@EqualsAndHashCode(callSuper = true)
@AllArgsConstructor
@NoArgsConstructor
@SuperBuilder
public class Renter extends Occupant {
	
	// number of property owned
	@Column(nullable = false, columnDefinition = "int default 0")
	private int propertyRented;

}
