package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.User.User;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class KeyTest {

    Role mockedRole = mock(Role.class);
    User mockedUser = mock(User.class);
    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        Key testKey = new Key("key", true, true, 1, mockedRole, mockedUser);

        // Assert
        assertEquals("key", testKey.getKey());
        assertTrue(testKey.isRevoked());
        assertTrue(testKey.isActive());
        assertEquals(1, testKey.getCompanyId());
        assertEquals(mockedRole, testKey.getRole());
        assertEquals(mockedUser, testKey.getUser());
    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        Key testKey = new Key();

        // Assert
        assertNull(testKey.getId());
        assertFalse(testKey.isRevoked());
        assertFalse(testKey.isActive());
        assertNull(testKey.getCompanyId());
        assertNull(testKey.getRole());
        assertNull(testKey.getUser());
    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        Key testKey = Key.builder().build();

        // Assert
        assertNull(testKey.getId());
        assertFalse(testKey.isRevoked());
        assertFalse(testKey.isActive());
        assertNull(testKey.getCompanyId());
        assertNull(testKey.getRole());
        assertNull(testKey.getUser());
    }
}
