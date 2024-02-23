package com.rently.rentlyAPI.auth.service;

import com.rently.rentlyAPI.auth.domain.dto.RegisterRequestDto;
import com.rently.rentlyAPI.domain.entity.User;
import com.rently.rentlyAPI.auth.repository.UserRepository;
import com.rently.rentlyAPI.auth.domain.dto.ChangePasswordRequestDto;
import lombok.AllArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.security.Principal;
import java.util.Optional;

@Service
@AllArgsConstructor
public class UserService {

    private final PasswordEncoder passwordEncoder;
    private final UserRepository repository;
    
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
        
	    return repository.save(user);
    }
    
    public void changePassword(ChangePasswordRequestDto request, Principal connectedUser) {

        var user = (User) ((UsernamePasswordAuthenticationToken) connectedUser).getPrincipal();

        // check if the current password is correct
        if (!passwordEncoder.matches(request.getCurrentPassword(), user.getPassword())) {
            throw new IllegalStateException("Wrong password");
        }
        // check if the two new passwords are the same
        if (!request.getNewPassword().equals(request.getConfirmationPassword())) {
            throw new IllegalStateException("Password are not the same");
        }

        // update the password
        user.setPassword(passwordEncoder.encode(request.getNewPassword()));

        // save the new password
        repository.save(user);
    }
    
    public Optional<User> getUserByEmail(String email) {
        return repository.findByEmail(email);
    }
}
