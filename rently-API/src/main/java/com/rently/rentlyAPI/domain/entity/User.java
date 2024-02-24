package com.rently.rentlyAPI.domain.entity;

import com.rently.rentlyAPI.auth.domain.entity.token.AccessToken;
import com.rently.rentlyAPI.auth.domain.enums.Provider;
import com.rently.rentlyAPI.auth.domain.enums.Role;
import jakarta.persistence.*;
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

  private String firstname;

  private String lastname;

  private String phoneNumber;

  @Column(length = 1000)
  private String bio;

  @Column(unique = true)
  private String email;
  
  private String password;
  
  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "varchar(255) default 'RENTLY'")
  private Provider provider;

  @Enumerated(EnumType.STRING)
  @Column(nullable = false, columnDefinition = "varchar(255) default 'USER'")
  private Role role;

  @OneToMany(mappedBy = "user", cascade= CascadeType.REMOVE)
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
