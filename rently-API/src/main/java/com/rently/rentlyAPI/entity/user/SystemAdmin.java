package com.rently.rentlyAPI.entity.user;

import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;

@Entity
@Table(name = "system_admin")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemAdmin extends User {

}
