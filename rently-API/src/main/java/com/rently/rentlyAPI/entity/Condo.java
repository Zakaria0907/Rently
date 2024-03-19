package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.CondoStatus;
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
@Table(name = "condo", uniqueConstraints = {
        @UniqueConstraint(columnNames = {"building_id", "unitNumber"})
})
public class Condo extends AbstractEntity {

    // address is building address + condo number
    private String address;

    @NotBlank(message = "Unit number is required")
    private Integer unitNumber;

    private String description;

    @Column(unique = true)
    private String registrationKey;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'AVAILABLE'")
    private CondoStatus status;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "building_id")
    private Building building;

    // condo can be associated to 0 or 1 parking
    @OneToOne(mappedBy = "condo")
    private Parking parking;

    // condo can be associated to 0 or 1 locker
    @OneToOne(mappedBy = "condo")
    private Locker locker;
}
