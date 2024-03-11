package com.rently.rentlyAPI.entity.auth;

import com.rently.rentlyAPI.auth.entity.enums.Provider;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ProviderTest {

    @Test
    public void testRENTLY() {
        // Arrange

        // Act
        Provider testProvider = Provider.RENTLY;

        // Assert
        assertEquals(Provider.RENTLY, testProvider);
    }

    @Test
    public void testGOOGLE() {
        // Arrange

        // Act
        Provider testProvider = Provider.GOOGLE;

        // Assert
        assertEquals(Provider.GOOGLE, testProvider);
    }

}
