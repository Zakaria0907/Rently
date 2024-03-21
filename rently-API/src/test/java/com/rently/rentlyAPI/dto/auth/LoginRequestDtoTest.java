//package com.rently.rentlyAPI.dto.auth;
//
//import com.rently.rentlyAPI.auth.dto.LoginRequestDto;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//
//public class LoginRequestDtoTest {
//
//    @Test
//    public void testAllArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        LoginRequestDto testLoginRequestDto = new LoginRequestDto("bingbong@gmail.com", "password");
//
//        // Assert
//        assertEquals("bingbong@gmail.com", testLoginRequestDto.getEmail());
//        assertEquals("password", testLoginRequestDto.getPassword());
//
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        LoginRequestDto testLoginRequestDto = new LoginRequestDto();
//
//        // Assert
//        assertNull(testLoginRequestDto.getEmail());
//        assertNull(testLoginRequestDto.getPassword());
//
//    }
//
//    @Test
//    public void testBuilderConstructor() {
//        // Arrange
//
//
//        // Act
//        LoginRequestDto testLoginRequestDto = LoginRequestDto.builder().build();
//
//        // Assert
//        assertNull(testLoginRequestDto.getEmail());
//        assertNull(testLoginRequestDto.getPassword());
//
//    }
//
//}
