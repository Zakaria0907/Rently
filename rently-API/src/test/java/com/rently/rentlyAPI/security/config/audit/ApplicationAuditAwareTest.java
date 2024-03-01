package com.rently.rentlyAPI.security.config.audit;

import org.junit.jupiter.api.Test;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ApplicationAuditAwareTest {

    @Test
    public void testGetCurrentAuditor() throws InterruptedException {
        // Arrange
        ApplicationAuditAware testApplicationAuditAware = new ApplicationAuditAware();

        // Act
        Optional<Integer> testOptionalInteger = testApplicationAuditAware.getCurrentAuditor();

        // Assert
        assertEquals(Optional.empty(), testOptionalInteger);

    }

}
