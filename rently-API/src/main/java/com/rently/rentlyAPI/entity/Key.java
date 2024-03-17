package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.User.User;
import com.rently.rentlyAPI.security.Role;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.SuperBuilder;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity(name = "Key")
public class Key extends AbstractEntity{


    @Column(unique = true, nullable = false, columnDefinition = "varchar(1000)")
    public String key;

    public boolean revoked;

    public boolean isActive;

    public Integer companyId;

    //role
    public Role role;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    public User user;
}
