package com.rently.rentlyAPI.entity.User;

import com.rently.rentlyAPI.entity.enums.EmployeeType;
import com.rently.rentlyAPI.security.Role;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
public class Employee extends User{
    
    // number of assignment completed
    @Column(nullable = false, columnDefinition = "int default 0")
    private int assignmentCount;

}
