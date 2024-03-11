package com.rently.rentlyAPI.dto.auth;

import com.rently.rentlyAPI.auth.dto.RegisterRequestDto;
import com.rently.rentlyAPI.auth.entity.Provider;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.mockito.Mockito.mock;

public class RegisterRequestDtoTest {

    Role mockedRole = mock(Role.class);
    @Test
    public void testAllArgsConstructor() {
        // Arrange


        // Act
        RegisterRequestDto testRegisterRequestDto = new RegisterRequestDto(1, "Bing", "Bong", "bingbong@gmail.com"
            , "password", "(123) 456-7890", "biography", mockedRole, Provider.GOOGLE);

        // Assert
        assertEquals(1, testRegisterRequestDto.getId());
        assertEquals("Bing", testRegisterRequestDto.getFirstname());
        assertEquals("Bong", testRegisterRequestDto.getLastname());
        assertEquals("bingbong@gmail.com", testRegisterRequestDto.getEmail());
        assertEquals("password", testRegisterRequestDto.getPassword());
        assertEquals("(123) 456-7890", testRegisterRequestDto.getPhoneNumber());
        assertEquals("biography", testRegisterRequestDto.getBio());
        assertEquals(mockedRole, testRegisterRequestDto.getRole());
        assertEquals(Provider.GOOGLE, testRegisterRequestDto.getProvider());

    }

    @Test
    public void testNoArgsConstructor() {
        // Arrange


        // Act
        RegisterRequestDto testRegisterRequestDto = new RegisterRequestDto();

        // Assert
        assertNull(testRegisterRequestDto.getId());
        assertNull(testRegisterRequestDto.getFirstname());
        assertNull(testRegisterRequestDto.getLastname());
        assertNull(testRegisterRequestDto.getEmail());
        assertNull(testRegisterRequestDto.getPassword());
        assertNull(testRegisterRequestDto.getPhoneNumber());
        assertNull(testRegisterRequestDto.getBio());
        assertEquals(Role.COMPANY, testRegisterRequestDto.getRole()); // Role.COMPANY is default
        assertEquals(Provider.RENTLY, testRegisterRequestDto.getProvider()); // Provider.RENTLY is default

    }

    @Test
    public void testBuilder() {
        // Arrange


        // Act
        RegisterRequestDto testRegisterRequestDto = RegisterRequestDto.builder().build();

        // Assert
        assertNull(testRegisterRequestDto.getId());
        assertNull(testRegisterRequestDto.getFirstname());
        assertNull(testRegisterRequestDto.getLastname());
        assertNull(testRegisterRequestDto.getEmail());
        assertNull(testRegisterRequestDto.getPassword());
        assertNull(testRegisterRequestDto.getPhoneNumber());
        assertNull(testRegisterRequestDto.getBio());
        assertEquals(Role.COMPANY, testRegisterRequestDto.getRole()); // Role.COMPANY is default
        assertEquals(Provider.RENTLY, testRegisterRequestDto.getProvider()); // Provider.RENTLY is default

    }

}
