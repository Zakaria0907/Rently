package com.rently.rentlyAPI.entity.auth;

import com.rently.rentlyAPI.auth.entity.AccessToken;
import com.rently.rentlyAPI.auth.entity.RefreshToken;
import com.rently.rentlyAPI.auth.entity.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class RefreshTokenTest {


    AccessToken mockedAccessToken = mock(AccessToken.class);
    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        RefreshToken testRefreshToken = new RefreshToken(1, "123abc", TokenType.BEARER, true, true, mockedAccessToken);

        // Assert
        assertEquals(1, testRefreshToken.getId());
        assertEquals("123abc", testRefreshToken.getRefreshToken());
        assertEquals(TokenType.BEARER, testRefreshToken.getTokenType());
        assertTrue(testRefreshToken.isRevoked());
        assertTrue(testRefreshToken.isExpired());
        assertEquals(mockedAccessToken, testRefreshToken.getAccessToken());

    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        RefreshToken testRefreshToken = new RefreshToken();

        // Assert
        assertNull(testRefreshToken.getId());
        assertNull(testRefreshToken.getRefreshToken());
        assertEquals(TokenType.BEARER, testRefreshToken.getTokenType());
        assertFalse(testRefreshToken.isRevoked());
        assertFalse(testRefreshToken.isExpired());
        assertNull(testRefreshToken.getAccessToken());

    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        RefreshToken testRefreshToken = RefreshToken.builder().build();

        // Assert
        assertNull(testRefreshToken.getId());
        assertNull(testRefreshToken.getRefreshToken());
        assertNull(testRefreshToken.getTokenType());
        assertFalse(testRefreshToken.isRevoked());
        assertFalse(testRefreshToken.isExpired());
        assertNull(testRefreshToken.getAccessToken());

    }

}
