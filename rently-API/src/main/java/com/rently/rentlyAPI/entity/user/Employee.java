package com.rently.rentlyAPI.entity.user;

import com.rently.rentlyAPI.entity.Company;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee extends User {

    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    // number of assignment completed
    @Column(nullable = false, columnDefinition = "int default 0")
    private int assignmentCount;

}
