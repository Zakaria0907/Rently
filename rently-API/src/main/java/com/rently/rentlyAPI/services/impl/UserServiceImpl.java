package com.rently.rentlyAPI.services.impl;

import com.rently.rentlyAPI.dto.auth.ChangePasswordRequestDto;
import com.rently.rentlyAPI.dto.auth.RegisterRequestDto;
import com.rently.rentlyAPI.entity.Key;
import com.rently.rentlyAPI.entity.User;
import com.rently.rentlyAPI.exceptions.AuthenticationException;
import com.rently.rentlyAPI.exceptions.OperationNonPermittedException;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.security.Role;
import com.rently.rentlyAPI.services.KeyService;
import com.rently.rentlyAPI.services.UserService;
import jakarta.persistence.EntityNotFoundException;
import lombok.AllArgsConstructor;
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
    private final KeyService keyService;

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

    //user becomes RENTER by providing key (user cannot go from owner -> renter)
    public User activateKeyToChangeRole(String condoKey) {

        //find the key
        Key key = keyService.findByKey(condoKey);

        //if key is not found
        if (key == null) {
            throw new EntityNotFoundException("Key with key " + condoKey + " not found");
        }

        //find user by key
         User user = userRepository.findUserByKey(condoKey);

            //if user is not found
            if (user == null) {
                throw new EntityNotFoundException("User with key " + condoKey + " not found");
            }

            //if user is not a renter
            if (user.getRole() != Role.USER) {
                throw new OperationNonPermittedException("User with key " + condoKey + " is not allowed to become a RENTER");
            }

            //if key role is not renter or owner
            if (key.getRole() != Role.RENTER && key.getRole() != Role.OWNER) {
                throw new OperationNonPermittedException("Your key ROLE is " + key.getRole() + " and it is not an allowed role.");
            }

            user.setRole(key.getRole());
            key.setActive(true);
            key.setUser(user);
            keyService.save(key);
            return userRepository.save(user);
    }


    @Override
    public Optional<User> findByEmail(String email) {
        return userRepository.findByEmail(email);
    }
}
