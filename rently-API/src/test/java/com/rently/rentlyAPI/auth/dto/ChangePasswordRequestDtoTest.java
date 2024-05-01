package com.rently.rentlyAPI.auth.dto;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class ChangePasswordRequestDtoTest {

    @Test
    void testAllArgsConstructor() {
        ChangePasswordRequestDto dto = new ChangePasswordRequestDto("old_password", "new_password", "confirm_password");

        assertEquals("old_password", dto.getOldPassword());
        assertEquals("new_password", dto.getNewPassword());
        assertEquals("confirm_password", dto.getConfirmationPassword());
    }

    @Test
    void testBuilder() {
        ChangePasswordRequestDto dto = ChangePasswordRequestDto.builder()
                .oldPassword("old_password")
                .newPassword("new_password")
                .confirmationPassword("confirm_password")
                .build();

        assertEquals("old_password", dto.getOldPassword());
        assertEquals("new_password", dto.getNewPassword());
        assertEquals("confirm_password", dto.getConfirmationPassword());
    }
}
