package com.rently.rentlyAPI.security;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PermissionTest {

    @Test
    public void testPermissionADMIN_READ() {
        // Arrange
        Permission testPermission = Permission.ADMIN_READ;

        // Act


        // Assert
        assertEquals(Permission.ADMIN_READ, testPermission);

    }

    @Test
    public void testPermissionADMIN_UPDATE() {
        // Arrange
        Permission testPermission = Permission.ADMIN_UPDATE;

        // Act


        // Assert
        assertEquals(Permission.ADMIN_UPDATE, testPermission);

    }
    @Test
    public void testPermissionADMIN_CREATE() {
        // Arrange
        Permission testPermission = Permission.ADMIN_CREATE;

        // Act


        // Assert
        assertEquals(Permission.ADMIN_CREATE, testPermission);

    }
    @Test
    public void testPermissionADMIN_DELETE() {
        // Arrange
        Permission testPermission = Permission.ADMIN_DELETE;

        // Act


        // Assert
        assertEquals(Permission.ADMIN_DELETE, testPermission);

    }
    @Test
    public void testPermissionCOMPANY_READ() {
        // Arrange
        Permission testPermission = Permission.COMPANY_READ;

        // Act


        // Assert
        assertEquals(Permission.COMPANY_READ, testPermission);

    }
    @Test
    public void testPermissionCOMPANY_UPDATE() {
        // Arrange
        Permission testPermission = Permission.COMPANY_UPDATE;

        // Act


        // Assert
        assertEquals(Permission.COMPANY_UPDATE, testPermission);

    }
    @Test
    public void testPermissionCOMPANY_CREATE() {
        // Arrange
        Permission testPermission = Permission.COMPANY_CREATE;

        // Act


        // Assert
        assertEquals(Permission.COMPANY_CREATE, testPermission);

    }
    @Test
    public void testPermissionCOMPANY_DELETE() {
        // Arrange
        Permission testPermission = Permission.COMPANY_DELETE;

        // Act


        // Assert
        assertEquals(Permission.COMPANY_DELETE, testPermission);

    }
    @Test
    public void testPermissionOWNER_READ() {
        // Arrange
        Permission testPermission = Permission.OWNER_READ;

        // Act


        // Assert
        assertEquals(Permission.OWNER_READ, testPermission);

    }
    @Test
    public void testPermissionOWNER_UPDATE() {
        // Arrange
        Permission testPermission = Permission.OWNER_UPDATE;

        // Act


        // Assert
        assertEquals(Permission.OWNER_UPDATE, testPermission);

    }
    @Test
    public void testPermissionOWNER_CREATE() {
        // Arrange
        Permission testPermission = Permission.OWNER_CREATE;

        // Act


        // Assert
        assertEquals(Permission.OWNER_CREATE, testPermission);

    }
    @Test
    public void testPermissionOWNER_DELETE() {
        // Arrange
        Permission testPermission = Permission.OWNER_DELETE;

        // Act


        // Assert
        assertEquals(Permission.OWNER_DELETE, testPermission);

    }
    @Test
    public void testPermissionRENTER_READ() {
        // Arrange
        Permission testPermission = Permission.RENTER_READ;

        // Act


        // Assert
        assertEquals(Permission.RENTER_READ, testPermission);

    }
    @Test
    public void testPermissionRENTER_UPDATE() {
        // Arrange
        Permission testPermission = Permission.RENTER_UPDATE;

        // Act


        // Assert
        assertEquals(Permission.RENTER_UPDATE, testPermission);

    }
    @Test
    public void testPermissionRENTER_CREATE() {
        // Arrange
        Permission testPermission = Permission.RENTER_CREATE;

        // Act


        // Assert
        assertEquals(Permission.RENTER_CREATE, testPermission);

    }
    @Test
    public void testPermissionRENTER_DELETE() {
        // Arrange
        Permission testPermission = Permission.RENTER_DELETE;

        // Act


        // Assert
        assertEquals(Permission.RENTER_DELETE, testPermission);

    }

    @Test
    public void testPermissionGetPermission() {
        // Arrange
        Permission testPermission = Permission.RENTER_DELETE;

        // Act


        // Assert
        assertEquals(Permission.RENTER_DELETE.getPermission(), testPermission.getPermission());
    }

}
