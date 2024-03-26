package com.rently.rentlyAPI.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;
import org.hibernate.annotations.ManyToAny;

@Entity
@Table(name = "condo_file")
@Data
@EqualsAndHashCode(callSuper = true)
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
public class CondoFile extends S3File {

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "condo_id")
    private Condo condo;

    @Column(length = 1024)
    private String description;
}
