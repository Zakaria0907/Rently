package com.rently.rentlyAPI.entity;

import jakarta.persistence.*;
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
@Table(name = "parking")
public class Parking extends AbstractEntity {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    @NotBlank(message = "The parking fee is required")
    private long parkingFee;

    //associated condo
    @OneToOne
    @JoinColumn(name = "condo_id", unique = true)
    private Condo condo;


}
