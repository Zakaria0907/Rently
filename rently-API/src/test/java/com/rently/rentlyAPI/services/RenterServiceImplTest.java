package com.rently.rentlyAPI.services;

import com.rently.rentlyAPI.entity.user.Renter;
import com.rently.rentlyAPI.repository.RenterRepository;
import com.rently.rentlyAPI.services.impl.RenterServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

class RenterServiceImplTest {

    @Mock
    private RenterRepository renterRepository;

    @InjectMocks
    private RenterServiceImpl renterService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    void testFindByEmail_Success() {
        String email = "test@example.com";
        Renter renter = new Renter();
        renter.setEmail(email);

        when(renterRepository.findByEmail(email)).thenReturn(Optional.of(renter));

        Optional<Renter> result = renterService.findByEmail(email);

        assertTrue(result.isPresent());
        assertEquals(email, result.get().getEmail());
    }

    @Test
    void testFindByEmail_NotFound() {
        String email = "test@example.com";

        when(renterRepository.findByEmail(email)).thenReturn(Optional.empty());

        Optional<Renter> result = renterService.findByEmail(email);

        assertFalse(result.isPresent());
    }

    @Test
    void testFindRenterEntityById_Success() {
        int id = 1;
        Renter renter = new Renter();
        renter.setId(id);

        when(renterRepository.findById(id)).thenReturn(Optional.of(renter));

        Optional<Renter> result = renterService.findRenterEntityById(id);

        assertTrue(result.isPresent());
        assertEquals(id, result.get().getId());
    }

    @Test
    void testFindRenterEntityById_NotFound() {
        int id = 1;

        when(renterRepository.findById(id)).thenReturn(Optional.empty());

        Optional<Renter> result = renterService.findRenterEntityById(id);

        assertFalse(result.isPresent());
    }
}
