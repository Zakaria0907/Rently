package com.rently.rentlyAPI.entity;

import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("RENTER")
public class Renter extends Occupant{
}
