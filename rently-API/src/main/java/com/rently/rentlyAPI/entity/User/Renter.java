package com.rently.rentlyAPI.entity.User;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "renter")
@Data
@EqualsAndHashCode(callSuper = true)
public class Renter extends Occupant {

}
