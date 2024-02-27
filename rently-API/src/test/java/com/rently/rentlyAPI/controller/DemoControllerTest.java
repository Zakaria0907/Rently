package com.rently.rentlyAPI.controller;

import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class DemoControllerTest {
    
    @Test
    public void testSayHello() {
        // Arrange
        DemoController testController = new DemoController();

        // Act
        ResponseEntity<String> testResponseEntity = testController.sayHello();

        // Assert
        assertEquals(HttpStatus.OK, testResponseEntity.getStatusCode());
        assertEquals(ResponseEntity.ok("Hello from secured endpoint"), testResponseEntity);
    }
}
