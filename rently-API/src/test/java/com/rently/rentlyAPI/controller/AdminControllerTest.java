package com.rently.rentlyAPI.controller;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class AdminControllerTest {


    @Test
    public void testGet() {
        // Arrange
        AdminController testController = new AdminController();

        // Act


        // Assert
        assertEquals("GET:: admin controller", testController.get());

    }

    @Test
    public void testPost() {
        // Arrange
        AdminController testController = new AdminController();

        // Act


        // Assert
        assertEquals("POST:: admin controller", testController.post());

    }

    @Test
    public void testPut() {
        // Arrange
        AdminController testController = new AdminController();

        // Act


        // Assert
        assertEquals("PUT:: admin controller", testController.put());

    }

    @Test
    public void testDelete() {
        // Arrange
        AdminController testController = new AdminController();

        // Act


        // Assert
        assertEquals("DELETE:: admin controller", testController.delete());

    }
}
