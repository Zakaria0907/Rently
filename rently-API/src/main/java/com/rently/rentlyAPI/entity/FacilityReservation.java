package com.rently.rentlyAPI.entity;


import jakarta.persistence.Entity;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "facility_reservation")
public class FacilityReservation extends AbstractEntity{
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "common_facility_id")
    private CommonFacility commonFacility;
    @ManyToOne
    @JoinColumn(name = "occupant_id")
    private Occupant occupant;

    private long duration;
    private String date;

}
