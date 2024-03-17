package com.rently.rentlyAPI.entity.User;

import com.rently.rentlyAPI.security.Role;
import jakarta.persistence.*;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import lombok.experimental.SuperBuilder;

@Entity
@Table(name = "system_admin")
@Data
@EqualsAndHashCode(callSuper = true)
public class SystemAdmin extends User {

}
