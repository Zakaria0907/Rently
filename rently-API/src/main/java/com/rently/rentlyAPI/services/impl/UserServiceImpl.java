package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.auth.ChangePasswordRequestDto;
import com.rently.rentlyAPI.dto.auth.RegisterRequestDto;
import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.services.UserService;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserServiceImpl implements UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository userRepository;

    public User createUser(RegisterRequestDto request) {
        String encodedPassword = null;
        
        if (request.getPassword() != null) {
            // Encode the password if provided (null with google for example)
            encodedPassword = passwordEncoder.encode(request.getPassword());
        }
        var user = User.builder()
            .firstname(request.getFirstname())
            .lastname(request.getLastname())
            .email(request.getEmail())
            .password(encodedPassword)
            .role(request.getRole())
            .provider(request.getProvider())
            .build();
        
	    return userRepository.save(user);
    }
    
    public void changePassword(ChangePasswordRequestDto request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new AuthenticationException("The current password is incorrect");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new AuthenticationException("The two passwords do not match");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        userRepository.save(user);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
