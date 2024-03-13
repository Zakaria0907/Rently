package com.rently.rentlyAPI.entity;

import jakarta.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "occupant_type", discriminatorType = DiscriminatorType.STRING)
public abstract class Occupant extends User{


}
