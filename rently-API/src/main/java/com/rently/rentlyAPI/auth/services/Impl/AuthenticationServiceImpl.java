package com.rently.rentlyAPI.auth.services.Impl;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.rently.rentlyAPI.auth.dto.AuthenticationResponseDto;
import com.rently.rentlyAPI.auth.dto.ChangePasswordRequestDto;
import com.rently.rentlyAPI.auth.dto.LoginRequestDto;
import com.rently.rentlyAPI.auth.services.AuthenticationService;
import com.rently.rentlyAPI.dto.RootUserDto;
import com.rently.rentlyAPI.entity.user.User;
import com.rently.rentlyAPI.repository.UserRepository;
import com.rently.rentlyAPI.utils.JwtUtils;
import com.rently.rentlyAPI.services.UserService;
import com.rently.rentlyAPI.validators.ObjectsValidator;

import io.jsonwebtoken.io.IOException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.RequiredArgsConstructor;

import jakarta.servlet.http.Cookie;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

/**
 * The AuthenticationService class is responsible for handling user authentication and registration.
 */
@Service
@RequiredArgsConstructor
public class AuthenticationServiceImpl implements AuthenticationService {
  private final UserRepository userRepository;
  private final JwtUtils jwtUtils;
  private final AuthenticationManager authenticationManager;
  private final UserService userService;
  private final ObjectsValidator<Object> validator;
  
  private final PasswordEncoder passwordEncoder;



  public AuthenticationResponseDto authenticate(LoginRequestDto loginRequestDto, HttpServletResponse response) {
    User user = userService.findUserAccordingToTypeWithEmail(loginRequestDto.getEmail());

    //    // Check if the user's email is associated with a third party provider
//    if (!user.getProvider().equals(Provider.RENTLY) && (loginRequestDto.getPassword() != null)) {
//      // Return a forbidden response with the appropriate message
//      throw new AuthenticationException("This email is already associated with an account using a third-party provider: Google");
//    }

    // Proceed with authentication
    authenticationManager.authenticate(
            new UsernamePasswordAuthenticationToken(
                    loginRequestDto.getEmail(),
                    loginRequestDto.getPassword()
            )
    );

    String jwtToken = jwtUtils.generateToken(user, false);
    String refreshToken = jwtUtils.generateToken(user, true);

    jwtUtils.addTokensAsCookies(response, jwtToken, refreshToken);
    RootUserDto userDto = RootUserDto.toDto(user);

    return buildAuthenticationResponse(jwtToken, refreshToken, userDto);
  }
  
  @Override
  public String changePassword(String token, ChangePasswordRequestDto changePasswordRequestDto) {
    String tokenWithoutBearer = token.substring(7);
    String email = jwtUtils.extractUsername(tokenWithoutBearer);
    User user = userService.findUserAccordingToTypeWithEmail(email);
    
    
    if(passwordEncoder.matches(changePasswordRequestDto.getOldPassword(), user.getPassword()) && changePasswordRequestDto.getNewPassword().equals(changePasswordRequestDto.getConfirmationPassword())){
      String newEncodedPassword = passwordEncoder.encode(changePasswordRequestDto.getNewPassword());
      user.setPassword(newEncodedPassword);
      userRepository.save(user);
      
      return "Password changed successfully";
    } else {
      return "Old password is incorrect";
    }
  }


 /**
* The refreshToken method is responsible for refreshing the user's access token, the user refresh token is used to generate a new access token and refresh token.
* We retrieve the refresh token from the request cookies and use it to get the user's email.
* We then use the user's email to get the user from the database.
* The old tokens are revoked and the new tokens are saved in the database and sent as cookies in the response.
* @param request The HttpServletRequest object
* @param response The HttpServletResponse object
* @throws IOException If an input or output exception occurs
* @throws UsernameNotFoundException If the user is not found
* @throws EntityNotFoundException If the refresh token is not found
* @throws AuthenticationException If the refresh token is revoked or invalid
*/
 public void refreshToken(HttpServletRequest request, HttpServletResponse response) throws IOException, java.io.IOException {
   Cookie[] cookies = request.getCookies();

   String refreshToken = null;
   if (cookies != null) {
     for (Cookie cookie : cookies) {
       if (cookie.getName().equals("refreshToken")) {
         refreshToken = cookie.getValue();
         break;
       }
     }
   }

   if (refreshToken == null) {
     response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
     return;
   }

   String userEmail = jwtUtils.extractUsername(refreshToken);
   System.out.println("userEmail in AuthenticationService: " + userEmail);

   User user = userService.findUserAccordingToTypeWithEmail(userEmail);

   System.out.println("user in AuthenticationService: " + user.getRole());

   String accessToken = jwtUtils.generateToken(user, false);
   String newRefreshToken = jwtUtils.generateToken(user, true);

   jwtUtils.addTokensAsCookies(response, accessToken, newRefreshToken);

   AuthenticationResponseDto authResponse = buildAuthenticationResponse(accessToken, newRefreshToken, RootUserDto.toDto(user));
   new ObjectMapper().writeValue(response.getOutputStream(), authResponse);
 }

  // Helper methods
  public AuthenticationResponseDto buildAuthenticationResponse(String jwtToken, String refreshToken, RootUserDto userDto) {
    return AuthenticationResponseDto.builder()
            .accessToken(jwtToken)
            .refreshToken(refreshToken)
            .user(userDto)
            .build();
  }

}
