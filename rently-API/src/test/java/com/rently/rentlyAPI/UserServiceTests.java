package com.rently.rentlyAPI;

import com.rently.rentlyAPI.auth.domain.dto.ChangePasswordRequestDto;
import com.rently.rentlyAPI.auth.domain.entity.User;
import com.rently.rentlyAPI.auth.repository.UserRepository;
import com.rently.rentlyAPI.auth.service.UserService;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.security.Principal;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class UserServiceTests {

    @Mock
    public PasswordEncoder passwordEncoder;

    @Mock
    public UserRepository userRepository;

    @InjectMocks
    public UserService userService;

    @Test
    void changePassword_SuccessfulChange() {
        // Arrange
        ChangePasswordRequestDto request = new ChangePasswordRequestDto("oldPassword", "newPassword", "newPassword");

        User user = User.builder()
                .id(1)
                .email("test@example.com")
                .password(passwordEncoder.encode("oldPassword"))
                .build();

        Principal principal = new UsernamePasswordAuthenticationToken(user, null);

        when(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())).thenReturn(true);

        // Act
        userService.changePassword(request, principal);

        // Assert
        verify(passwordEncoder).matches(request.getCurrentPassword(), user.getPassword());
        verify(userRepository).save(user);
    }

    @Test
    void changePassword_WrongCurrentPassword() {
        // Arrange
        ChangePasswordRequestDto request = new ChangePasswordRequestDto("oldPassword", "newPassword", "newPassword");

        User user = User.builder()
                .id(1)
                .email("test@example.com")
                .password(passwordEncoder.encode("oldPassword"))
                .build();

        Principal principal = new UsernamePasswordAuthenticationToken(user, null);

        when(passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())).thenReturn(false);

        // Act and Assert
        assertThrows(IllegalStateException.class, () -> userService.changePassword(request, principal));

        // Verify that save is not called
        verify(userRepository, never()).save(user);
    }


}
