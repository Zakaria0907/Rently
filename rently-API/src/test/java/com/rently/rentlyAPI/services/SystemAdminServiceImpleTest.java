package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.user.SystemAdmin;
import com.rently.rentlyAPI.repository.SystemAdminRepository;
import com.rently.rentlyAPI.services.impl.SystemAdminServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static org.mockito.Mockito.when;

class SystemAdminServiceImplTest {

    @Mock
    private SystemAdminRepository systemAdminRepository;


    @InjectMocks
    private SystemAdminServiceImpl systemAdminService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail_Success() {
        String email = "test@example.com";
        SystemAdmin systemAdmin = new SystemAdmin();
        systemAdmin.setEmail(email);

        when(systemAdminRepository.findByEmail(email)).thenReturn(Optional.of(systemAdmin));

        Optional<SystemAdmin> result = systemAdminService.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }


}
