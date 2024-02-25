package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.auth.Provider;
import com.rently.rentlyAPI.entity.enums.CondoStatus;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "condo")
public class Condo extends AbstractEntity{

    private String name;

    private String address;

    private String condoNumber;

    private String condoType;

    private String description;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'AVAILABLE'")
    private CondoStatus status;


    @JoinColumn(name = "owned_by_id")
    @ManyToOne
    @Nullable
    private User ownerId;

    @JoinColumn(name = "rented_by_id")
    @ManyToOne
    @Nullable
    private User tenantId;


}
