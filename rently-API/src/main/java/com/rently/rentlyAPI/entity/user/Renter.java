package com.rently.rentlyAPI.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "renter")
@Data
@EqualsAndHashCode(callSuper = true)
public class Renter extends Occupant {

}
