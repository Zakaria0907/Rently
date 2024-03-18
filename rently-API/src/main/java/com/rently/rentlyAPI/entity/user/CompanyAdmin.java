package com.rently.rentlyAPI.entity.user;

import com.rently.rentlyAPI.entity.Company;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.Cascade;

@Entity
@Table(name = "company_admin")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CompanyAdmin extends User {
	
	@ManyToOne
	@JoinColumn(name="company_id", nullable=false)
	private Company company;
}
