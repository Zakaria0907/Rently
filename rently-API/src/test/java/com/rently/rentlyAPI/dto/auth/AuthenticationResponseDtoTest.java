//package com.rently.rentlyAPI.dto.auth;
//
//import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
//import com.rently.rentlyAPI.dto.UserDto;
//import org.junit.jupiter.api.Test;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.junit.jupiter.api.Assertions.assertNull;
//import static org.mockito.Mockito.mock;
//
//public class AuthenticationResponseDtoTest {
//
//    UserDto mockedUserDto = mock(UserDto.class);
//    @Test
//    public void testAllArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        AuthenticationResponseDto testAuthenticationResponseDto = new AuthenticationResponseDto("123", "456", mockedUserDto);
//
//        // Assert
//        assertEquals("123", testAuthenticationResponseDto.getAccessToken());
//        assertEquals("456", testAuthenticationResponseDto.getRefreshToken());
//        assertEquals(mockedUserDto, testAuthenticationResponseDto.getUser());
//
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        // Arrange
//
//
//        // Act
//        AuthenticationResponseDto testAuthenticationResponseDto = new AuthenticationResponseDto();
//
//        // Assert
//        assertNull(testAuthenticationResponseDto.getAccessToken());
//        assertNull(testAuthenticationResponseDto.getRefreshToken());
//        assertNull(testAuthenticationResponseDto.getUser());
//
//    }
//
//    @Test
//    public void testBuilderConstructor() {
//        // Arrange
//
//
//        // Act
//        AuthenticationResponseDto testAuthenticationResponseDto = AuthenticationResponseDto.builder().build();
//
//        // Assert
//        assertNull(testAuthenticationResponseDto.getAccessToken());
//        assertNull(testAuthenticationResponseDto.getRefreshToken());
//        assertNull(testAuthenticationResponseDto.getUser());
//
//    }
//
//}
