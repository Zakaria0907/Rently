package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.auth.AccessToken;
import com.rently.rentlyAPI.entity.auth.Provider;
import com.rently.rentlyAPI.security.Role;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import lombok.*;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
@Entity
@ToString
@Table(name = "_user")
public class User implements UserDetails {

    @Id
    @GeneratedValue
    private Integer id;

    @NotNull(message = "The first name is required")
    @NotBlank(message = "The first name is required")
    @NotEmpty(message = "The first name is required")
    private String firstname;

    @NotNull(message = "The last name is required")
    @NotBlank(message = "The last name is required")
    @NotEmpty(message = "The last name is required")
    private String lastname;

    @Column(unique = true)
    @NotNull(message = "The email is required")
    @NotBlank(message = "The email is required")
    @NotEmpty(message = "The email is required")
    private String email;

    @NotNull(message = "The password is required")
    @NotBlank(message = "The password is required")
    @NotEmpty(message = "The password is required")
    private String password;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'RENTLY'")
    private Provider provider;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false, columnDefinition = "varchar(255) default 'USER'")
    private Role role;

    @OneToMany(mappedBy = "user", cascade = CascadeType.REMOVE)
    private List<AccessToken> accessTokens;

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
