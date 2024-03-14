package com.rently.rentlyAPI.entity;


import jakarta.persistence.DiscriminatorValue;
import jakarta.persistence.Entity;

@Entity
@DiscriminatorValue("OWNER")
public class Owner extends Occupant{


}
