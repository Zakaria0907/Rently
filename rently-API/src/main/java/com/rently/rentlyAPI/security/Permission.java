package com.rently.rentlyAPI.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {
    
    SYSTEM_ADMIN_CREATE("system_admin:create"),
    SYSTEM_ADMIN_READ("system_admin:read"),
    SYSTEM_ADMIN_UPDATE("system_admin:update"),
    SYSTEM_ADMIN_DELETE("system_admin:delete"),
    
    COMPANY_ADMIN_CREATE("company_admin:create"),
    COMPANY_ADMIN_READ("company_admin:read"),
    COMPANY_ADMIN_UPDATE("company_admin:update"),
    COMPANY_ADMIN_DELETE("company_admin:delete"),
    
    EMPLOYEE_CREATE("employee:create"),
    EMPLOYEE_READ("employee:read"),
    EMPLOYEE_UPDATE("employee:update"),
    EMPLOYEE_DELETE("employee:delete"),
    
    PUBLIC_USER_CREATE("public_user:create"),
    PUBLIC_USER_READ("public_user:read"),
    PUBLIC_USER_UPDATE("public_user:update"),
    PUBLIC_USER_DELETE("public_user:delete"),
    
    OWNER_CREATE("owner:create"),
    OWNER_READ("owner:read"),
    OWNER_UPDATE("owner:update"),
    OWNER_DELETE("owner:delete"),
    
    RENTER_CREATE("renter:create"),
    RENTER_READ("renter:read"),
    RENTER_UPDATE("renter:update"),
    RENTER_DELETE("renter:delete");
    
    private final String permission;
}
