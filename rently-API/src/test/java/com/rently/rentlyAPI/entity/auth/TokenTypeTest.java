package com.rently.rentlyAPI.entity.auth;

import com.rently.rentlyAPI.auth.entity.enums.TokenType;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class TokenTypeTest {

    @Test
    public void testConstructor() {
        // Arrange

        // Act
        TokenType testTokenType = TokenType.BEARER;

        // Assert
        assertEquals(TokenType.BEARER, testTokenType);
    }

}
