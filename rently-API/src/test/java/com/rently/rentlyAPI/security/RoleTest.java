package com.rently.rentlyAPI.security;

import org.junit.jupiter.api.Test;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class RoleTest {

    @Test
    public void testRoleGetAuthorities() {
        // Arrange
        Role testRole = Role.ADMIN;

        // Act
        List<SimpleGrantedAuthority> testList = testRole.getAuthorities();

        // Assert
        assertEquals(Role.ADMIN.getAuthorities(), testRole.getAuthorities());

    }

    @Test
    public void testRoleUSER() {
        // Arrange
        Role testRole = Role.USER;

        // Act


        // Assert
        assertEquals(Role.USER, testRole);

    }

    @Test
    public void testRoleADMIN() {
        // Arrange
        Role testRole = Role.ADMIN;

        // Act


        // Assert
        assertEquals(Role.ADMIN, testRole);

    }

    @Test
    public void testRoleCOMPANY() {
        // Arrange
        Role testRole = Role.COMPANY;

        // Act


        // Assert
        assertEquals(Role.COMPANY, testRole);

    }

    @Test
    public void testRoleOWNER() {
        // Arrange
        Role testRole = Role.OWNER;

        // Act


        // Assert
        assertEquals(Role.OWNER, testRole);

    }

    @Test
    public void testRoleRENTER() {
        // Arrange
        Role testRole = Role.RENTER;

        // Act


        // Assert
        assertEquals(Role.RENTER, testRole);

    }

    @Test
    public void testPermissionGetPermission() {
        // Arrange
        Role testRole = Role.RENTER;

        // Act


        // Assert
        assertEquals(Role.RENTER.getPermissions(), testRole.getPermissions());
    }

}
