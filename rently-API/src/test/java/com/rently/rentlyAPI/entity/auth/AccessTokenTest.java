package com.rently.rentlyAPI.entity.auth;

import com.rently.rentlyAPI.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;

public class AccessTokenTest {

    RefreshToken mockedRefreshToken = mock(RefreshToken.class);
    User mockedUser = mock(User.class);

    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        AccessToken testAccessToken = new AccessToken(1, "123abc", TokenType.BEARER, true, true, mockedRefreshToken, mockedUser);

        // Assert
        assertEquals(1, testAccessToken.getId());
        assertEquals("123abc", testAccessToken.getToken());
        assertEquals(TokenType.BEARER, testAccessToken.getTokenType());
        assertTrue(testAccessToken.isRevoked());
        assertTrue(testAccessToken.isExpired());
        assertEquals(mockedRefreshToken, testAccessToken.getRefreshToken());
        assertEquals(mockedUser, testAccessToken.getUser());

    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        AccessToken testAccessToken = new AccessToken();

        // Assert
        assertNull(testAccessToken.getId());
        assertNull(testAccessToken.getToken());
        assertEquals(TokenType.BEARER, testAccessToken.getTokenType());
        assertFalse(testAccessToken.isRevoked());
        assertFalse(testAccessToken.isExpired());
        assertNull(testAccessToken.getRefreshToken());
        assertNull(testAccessToken.getUser());

    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        AccessToken testAccessToken = AccessToken.builder().build();

        // Assert
        assertNull(testAccessToken.getId());
        assertNull(testAccessToken.getToken());
        assertNull(testAccessToken.getTokenType());
        assertFalse(testAccessToken.isRevoked());
        assertFalse(testAccessToken.isExpired());
        assertNull(testAccessToken.getRefreshToken());
        assertNull(testAccessToken.getUser());

    }


    
}
