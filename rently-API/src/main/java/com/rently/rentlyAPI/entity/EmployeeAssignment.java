package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.WorkType;
import com.rently.rentlyAPI.entity.user.Employee;
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
@Table(name = "employee_assignment")
public class EmployeeAssignment extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;
    @ManyToOne
    @JoinColumn(nullable = true, name = "employee_id")
    private Employee employee;
    @ManyToOne
    @JoinColumn(name = "request_id")
    private OwnerRequest ownerRequest;
    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'GENERAL'")
    private WorkType workType;

}
