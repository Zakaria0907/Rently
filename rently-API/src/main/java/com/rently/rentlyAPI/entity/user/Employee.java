package com.rently.rentlyAPI.entity.user;

import com.rently.rentlyAPI.entity.Company;
import com.rently.rentlyAPI.entity.enums.EmployeeType;
import jakarta.persistence.*;
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
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'GENERAL'")
    private EmployeeType employeeType;

}
