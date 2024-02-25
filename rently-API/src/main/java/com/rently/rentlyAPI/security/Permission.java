package com.rently.rentlyAPI.security;

import lombok.Getter;
import lombok.RequiredArgsConstructor;

@Getter
@RequiredArgsConstructor
public enum Permission {

    ADMIN_READ("admin:read"),
    ADMIN_UPDATE("admin:update"),
    ADMIN_CREATE("admin:create"),
    ADMIN_DELETE("admin:delete"),
    COMPANY_READ("company:read"),
    COMPANY_UPDATE("company:update"),
    COMPANY_CREATE("company:create"),
    COMPANY_DELETE("company:delete")

    ;

    private final String permission;
}
