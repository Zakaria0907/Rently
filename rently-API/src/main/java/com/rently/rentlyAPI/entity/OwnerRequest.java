package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.enums.WorkType;
import com.rently.rentlyAPI.entity.user.Owner;
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
@Table(name = "owner_request")
public class OwnerRequest extends AbstractEntity {
    @ManyToOne
    @JoinColumn(name = "company_id")
    private Company company;

    @ManyToOne
    @JoinColumn(name = "owner_id")
    private Owner owner;

    @ManyToOne
    @JoinColumn(name = "building_id")
    private Building building;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'GENERAL'")
    private WorkType workType;

    private String requestDescription;


}
