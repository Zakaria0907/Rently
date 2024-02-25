package com.rently.rentlyAPI.security;

import com.rently.rentlyAPI.security.Permission;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

import static com.rently.rentlyAPI.security.Permission.*;


@Getter
@RequiredArgsConstructor
public enum Role {

  //User currently have no permission for protected resources
  USER(Collections.emptySet()),

  //Admin have all permissions
  ADMIN(
          Set.of(
                  ADMIN_READ,
                  ADMIN_UPDATE,
                  ADMIN_DELETE,
                  ADMIN_CREATE,
                  COMPANY_READ,
                  COMPANY_UPDATE,
                  COMPANY_DELETE,
                  COMPANY_CREATE
          )
  ),
  COMPANY(
          Set.of(
                  COMPANY_READ,
                  COMPANY_UPDATE,
                  COMPANY_DELETE,
                  COMPANY_CREATE
          )
  )

  ;

  private final Set<Permission> permissions;

  public List<SimpleGrantedAuthority> getAuthorities() {
    var authorities = getPermissions()
            .stream()
            .map(permission -> new SimpleGrantedAuthority(permission.getPermission()))
            .collect(Collectors.toList());
    authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
    return authorities;
  }
}
