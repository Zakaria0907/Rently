package com.rently.rentlyAPI.dto;

import com.rently.rentlyAPI.entity.Key;
import com.rently.rentlyAPI.entity.User;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

public class KeyDtoTest {

    Key mockedKey = mock(Key.class);
    KeyDto mockedKeyDto = mock(KeyDto.class);
    User mockedUser = mock(User.class);

    @Test
    public void testFromEntity() {
        // Arrange
        when(mockedKey.getUser()).thenReturn(mockedUser);


        // Act
        KeyDto testKeyDto = KeyDto.fromEntity(mockedKey);

        // Assert
        assertEquals(mockedKey.getKey(), testKeyDto.getKey());
        assertEquals(mockedKey.isRevoked(), testKeyDto.isRevoked());
        assertEquals(mockedKey.isActive(), testKeyDto.isActive());
        assertEquals(mockedKey.getCompanyId(), testKeyDto.getCompanyId());
        assertEquals(mockedKey.getUser().getId(), testKeyDto.getUserId());
        assertEquals(mockedKey.getRole(), testKeyDto.getRole());
    }

    @Test
    public void testToEntity() {
        // Arrange


        // Act
        Key testKey = KeyDto.toEntity(mockedKeyDto);

        // Assert
        assertEquals(mockedKeyDto.getKey(), testKey.getKey());
        assertEquals(mockedKeyDto.isRevoked(), testKey.isRevoked());
        assertEquals(mockedKeyDto.isActive(), testKey.isActive());
        assertEquals(mockedKeyDto.getCompanyId(), testKey.getCompanyId());
        assertEquals(mockedKeyDto.getRole(), testKey.getRole());
    }
    
}
