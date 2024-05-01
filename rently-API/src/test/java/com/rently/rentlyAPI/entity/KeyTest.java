package com.rently.rentlyAPI.entity;

import com.rently.rentlyAPI.entity.user.Owner;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

public class KeyTest {

    @Test
    void testAllArgsConstructor() {
        Key key = new Key("testKey", true, true, 1, Role.USER, new Owner());

        assertEquals("testKey", key.getKey());
        assertTrue(key.isRevoked());
        assertTrue(key.isActive());
        assertEquals(1, key.getCompanyId());
        assertEquals(Role.USER, key.getRole());
        assertNotNull(key.getUser());
    }

    @Test
    void testSuperBuilder() {
        Key key = Key.builder()
                .key("testKey")
                .revoked(true)
                .isActive(true)
                .companyId(1)
                .role(Role.USER)
                .user(new Owner())
                .build();

        assertEquals("testKey", key.getKey());
        assertTrue(key.isRevoked());
        assertTrue(key.isActive());
        assertEquals(1, key.getCompanyId());
        assertEquals(Role.USER, key.getRole());
        assertNotNull(key.getUser());
    }
}
