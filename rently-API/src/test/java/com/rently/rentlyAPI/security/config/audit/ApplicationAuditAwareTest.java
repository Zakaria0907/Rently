package com.rently.rentlyAPI.security.config.audit;

import com.rently.rentlyAPI.entity.User;
import org.springframework.security.core.Authentication;
import org.junit.jupiter.api.Test;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class ApplicationAuditAwareTest {

    Authentication mockedAuthentication = mock(Authentication.class);
    Object mockedObject = mock(Object.class);
    @Test
    public void testGetCurrentAuditor() {
        // Arrange
        ApplicationAuditAware testApplicationAuditAware = new ApplicationAuditAware();
        when(mockedAuthentication.getPrincipal()).thenReturn(mockedObject);

        // Act
        Optional<Integer> testOptionalInteger = testApplicationAuditAware.getCurrentAuditor();

        // Assert
        assertEquals(Optional.empty(), testOptionalInteger);

    }

}
