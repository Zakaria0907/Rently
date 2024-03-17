package com.rently.rentlyAPI.security;

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
  SYSTEM_ADMIN(
      Set.of(
          SYSTEM_ADMIN_CREATE,
          SYSTEM_ADMIN_READ,
          SYSTEM_ADMIN_UPDATE,
          SYSTEM_ADMIN_DELETE
      )
  ),
  
  COMPANY_ADMIN(
      Set.of(
          COMPANY_ADMIN_CREATE,
          COMPANY_ADMIN_READ,
          COMPANY_ADMIN_UPDATE,
          COMPANY_ADMIN_DELETE
      )
  ),
  
  EMPLOYEE(
      Set.of(
          EMPLOYEE_CREATE,
          EMPLOYEE_READ,
          EMPLOYEE_UPDATE,
          EMPLOYEE_DELETE
      )
  ),
  
  PUBLIC_USER(
      Set.of(
          PUBLIC_USER_CREATE,
          PUBLIC_USER_READ,
          PUBLIC_USER_UPDATE,
          PUBLIC_USER_DELETE
      )
  ),

  OWNER(
      Set.of(
          OWNER_CREATE,
          OWNER_READ,
          OWNER_UPDATE,
          OWNER_DELETE
      )
    ),

  RENTER(
      Set.of(
          RENTER_CREATE,
          RENTER_READ,
          RENTER_UPDATE,
          RENTER_DELETE
      )
  );

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
