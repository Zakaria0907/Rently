package com.rently.rentlyAPI.auth.utils;

import com.rently.rentlyAPI.auth.domain.dto.RegisterRequestDto;
import com.rently.rentlyAPI.auth.domain.entity.RentlyOAuth2User;
import com.rently.rentlyAPI.auth.domain.entity.User;
import com.rently.rentlyAPI.auth.domain.enums.Provider;
import com.rently.rentlyAPI.auth.domain.enums.Role;
import com.rently.rentlyAPI.auth.service.TokenService;
import com.rently.rentlyAPI.auth.service.UserService;
import com.rently.rentlyAPI.exception.AuthenticationException;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.Authentication;
import org.springframework.security.web.authentication.SavedRequestAwareAuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Optional;

@Component
@AllArgsConstructor
public class OAuth2LoginSuccessHandler extends SavedRequestAwareAuthenticationSuccessHandler {
	
	private final UserService userService;
	private final JwtUtils jwtUtils;
	private final TokenService tokenService;
	
	@Override
	public void onAuthenticationSuccess(HttpServletRequest request,
	                                    HttpServletResponse response,
	                                    Authentication authentication)
			throws ServletException, IOException
	{
		RentlyOAuth2User oAuth2User = (RentlyOAuth2User) authentication.getPrincipal();
		Optional<User> existingUser = userService.getUserByEmail(oAuth2User.getEmail());
		
		if (existingUser.isPresent()) {
			Provider provider = existingUser.get().getProvider();
			
			if(!provider.equals(Provider.RENTLY)){
				// User already exists with a third-party provider
				User user = existingUser.get();
				handleUserWithThirdPartyProvider(user, response);
			} else {
				// User already exists with Rently
				throw new AuthenticationException("This email is already associated with an account with Rently", HttpStatus.FORBIDDEN);
			}
			
		} else {
			// User does not exist, creating new user
			RegisterRequestDto requestDto = RegisterRequestDto.builder()
					.firstname( oAuth2User.getFirstName())
					.lastname(oAuth2User.getLastName())
					.email(oAuth2User.getEmail())
					.role(Role.USER)
					.password(null)
					.provider(Provider.valueOf(oAuth2User.getProvider().toUpperCase()))
					.build();
			
			User newSavedUser = userService.createUser(requestDto);
			handleUserWithThirdPartyProvider(newSavedUser, response);
		}
		
		response.sendRedirect("http://localhost:5173");

	}
	
	private void handleUserWithThirdPartyProvider(User user, HttpServletResponse response) {
		String jwtToken = jwtUtils.generateToken(user);
		String refreshToken = jwtUtils.generateRefreshToken(user);
		tokenService.revokeAllUserTokens(user);
		tokenService.revokeAllUserRefreshTokens(user);
		tokenService.saveUserTokens(user, jwtToken, refreshToken);
		jwtUtils.addTokensAsCookies(response, jwtToken, refreshToken);
	}
	
}
