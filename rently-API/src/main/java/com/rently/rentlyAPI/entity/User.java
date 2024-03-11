package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.auth.entity.AccessToken;
import com.rently.rentlyAPI.auth.entity.Provider;
import com.rently.rentlyAPI.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import lombok.experimental.SuperBuilder;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@SuperBuilder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "_user")
public class User extends AbstractEntity implements UserDetails {
    
    @Column(unique = true)
    @NotBlank(message = "The email is required")
    private String email;
    
    @NotBlank(message = "The password is required")
    private String password;
    
    @NotBlank(message = "The first name is required")
    private String firstname;
    
    @NotBlank(message = "The last name is required")
    private String lastname;
    
    private String phoneNumber;
    
    private String bio;
    
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "profile_picture_id", referencedColumnName = "id")
    private S3File profilePicture;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'RENTLY'")
    private Provider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'COMPANY'")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE, orphanRemoval = true)
    private List<AccessToken> accessTokens;

    @OneToMany(mappedBy = "user")
    private List<Condo> condos;

    @OneToMany(mappedBy = "user")
    private List<Key> registrationKey;
    
    @OneToMany(mappedBy = "company", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Building> buildings;

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return role.getAuthorities();
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
