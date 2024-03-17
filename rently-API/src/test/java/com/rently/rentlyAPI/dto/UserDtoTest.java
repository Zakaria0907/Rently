package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.user.User;
import com.rently.rentlyAPI.security.Role;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class UserDtoTest {

    UserDto mockedUserDto = mock(UserDto.class);
    User mockedUser = mock(User.class);
    Role mockedRole = mock(Role.class);

    @Test
    public void testFromEntity() {
        // Arrange
        when(mockedUser.getRole()).thenReturn(mockedRole);
        when(mockedRole.name()).thenReturn("ADMIN");


        // Act
        UserDto testUserDto = UserDto.fromEntity(mockedUser);

        // Assert
        assertEquals(mockedUser.getId(), testUserDto.getId());
        assertEquals(mockedUser.getFirstName(), testUserDto.getFirstname());
        assertEquals(mockedUser.getLastName(), testUserDto.getLastname());
        assertEquals(mockedUser.getPhoneNumber(), testUserDto.getPhoneNumber());
        assertEquals(mockedUser.getBio(), testUserDto.getBio());
        assertEquals(mockedUser.getEmail(), testUserDto.getEmail());
        assertEquals(mockedUser.getRole().name(), testUserDto.getRole());
    }

    @Test
    public void testToEntity() {
        // Arrange
        when(mockedUserDto.getRole()).thenReturn("ADMIN");


        // Act
        User testUser = UserDto.toEntity(mockedUserDto);

        // Assert
        assertEquals(mockedUserDto.getId(), testUser.getId());
        assertEquals(mockedUserDto.getFirstname(), testUser.getFirstName());
        assertEquals(mockedUserDto.getLastname(), testUser.getLastName());
        assertEquals(mockedUserDto.getPhoneNumber(), testUser.getPhoneNumber());
        assertEquals(mockedUserDto.getBio(), testUser.getBio());
        assertEquals(mockedUserDto.getEmail(), testUser.getEmail());
    }

}
