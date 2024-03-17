package com.rently.rentlyAPI.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

@Entity
@Table(name = "owner")
@Data
@EqualsAndHashCode(callSuper = true)
@NoArgsConstructor
@AllArgsConstructor
public class Owner extends Occupant {
	
	// number of property owned
	@Column(nullable = false, columnDefinition = "int default 0")
	private int propertyOwned;
	
	// number of request asked
	@Column(nullable = false, columnDefinition = "int default 0")
	private int requestCount;

}
