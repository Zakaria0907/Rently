package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.user.Occupant;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "housing_contract")
public class HousingContract extends AbstractEntity{
    
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
   
    @ManyToOne
    @JoinColumn(name = "occupant_id")
    private Occupant occupant;

    @ManyToOne
    @JoinColumn(name = "condo_id")
    private Condo condo;
    
    @Min(value = 0, message = "Monthly rent must be greater than 0")
    private Integer monthlyRent;
    
    @NotBlank(message = "Monthly rent is required")
    private String occupantType;
}
