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
                  SYSTEM_ADMIN_READ,
                  SYSTEM_ADMIN_UPDATE,
                  SYSTEM_ADMIN_DELETE,
                  SYSTEM_ADMIN_CREATE,
                  COMPANY_ADMIN_READ,
                  COMPANY_ADMIN_UPDATE,
                  COMPANY_ADMIN_DELETE,
                  COMPANY_ADMIN_CREATE
          )
  ),
  COMPANY_ADMIN(
          Set.of(
                  COMPANY_ADMIN_READ,
                  COMPANY_ADMIN_UPDATE,
                  COMPANY_ADMIN_DELETE,
                  COMPANY_ADMIN_CREATE
          )
  ),

  OWNER(
          Set.of(
                  OWNER_READ,
                  OWNER_UPDATE,
                  OWNER_DELETE,
                  OWNER_CREATE
          )
    ),

    RENTER(
            Set.of(
                    RENTER_READ,
                    RENTER_UPDATE,
                    RENTER_DELETE,
                    RENTER_CREATE
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
