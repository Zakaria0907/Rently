//package com.rently.rentlyAPI.dto.auth;
//
//import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//public class ChangePasswordRequestDtoTest {
//
//    @Test
//    public void testAllArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        ChangePasswordRequestDto testChangePasswordRequestDto = new ChangePasswordRequestDto("123", "456", "789");
//
//        // Assert
//        assertEquals("123", testChangePasswordRequestDto.getCurrentPassword());
//        assertEquals("456", testChangePasswordRequestDto.getNewPassword());
//        assertEquals("789", testChangePasswordRequestDto.getConfirmationPassword());
//    }
//
//    @Test
//    public void testBuilderConstructor() {
//        // Arrange
//
//
//        // Act
//        ChangePasswordRequestDto testChangePasswordRequestDto = ChangePasswordRequestDto.builder().build();
//
//        // Assert
//        assertNull(testChangePasswordRequestDto.getCurrentPassword());
//        assertNull(testChangePasswordRequestDto.getNewPassword());
//        assertNull(testChangePasswordRequestDto.getConfirmationPassword());
//
//    }
//
//    @Test
//    public void testSetters() {
//        // Arrange
//
//
//        // Act
//        ChangePasswordRequestDto testChangePasswordRequestDto = ChangePasswordRequestDto.builder().build();
//        testChangePasswordRequestDto.setCurrentPassword("bing");
//        testChangePasswordRequestDto.setNewPassword("bong");
//        testChangePasswordRequestDto.setConfirmationPassword("shling");
//
//        // Assert
//        assertEquals("bing", testChangePasswordRequestDto.getCurrentPassword());
//        assertEquals("bong", testChangePasswordRequestDto.getNewPassword());
//        assertEquals("shling", testChangePasswordRequestDto.getConfirmationPassword());
//
//    }
//
//}
