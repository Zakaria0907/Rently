package com.rently.rentlyAPI.entity;


import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "company")
public class Company extends AbstractEntity {
    @NotBlank(message = "Name is required")
    private String name;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Building> buildings;

    @OneToMany(mappedBy = "handlingCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<HousingContract> housingContracts;

    @OneToMany(mappedBy = "handlingCompany", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmploymentContract> employmentContracts;
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Request> requests;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<EmployeeAssignment> backlog;

    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<FacilityReservation> reservationCalendar;

}
