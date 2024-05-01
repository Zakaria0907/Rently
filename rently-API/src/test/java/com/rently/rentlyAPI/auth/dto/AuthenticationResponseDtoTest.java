package com.rently.rentlyAPI.auth.dto;

import com.rently.rentlyAPI.dto.RootUserDto;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

public class AuthenticationResponseDtoTest {

    @Test
    void testAllArgsConstructor() {
        RootUserDto user = new RootUserDto();
        AuthenticationResponseDto dto = new AuthenticationResponseDto("access_token", "refresh_token", user);

        assertEquals("access_token", dto.getAccessToken());
        assertEquals("refresh_token", dto.getRefreshToken());
        assertEquals(user, dto.getUser());
    }

    @Test
    void testNoArgsConstructor() {
        AuthenticationResponseDto dto = new AuthenticationResponseDto();

        assertNull(dto.getAccessToken());
        assertNull(dto.getRefreshToken());
        assertNull(dto.getUser());
    }

    @Test
    void testBuilder() {
        RootUserDto user = new RootUserDto();
        AuthenticationResponseDto dto = AuthenticationResponseDto.builder()
                .accessToken("access_token")
                .refreshToken("refresh_token")
                .user(user)
                .build();

        assertEquals("access_token", dto.getAccessToken());
        assertEquals("refresh_token", dto.getRefreshToken());
        assertEquals(user, dto.getUser());
    }


}
