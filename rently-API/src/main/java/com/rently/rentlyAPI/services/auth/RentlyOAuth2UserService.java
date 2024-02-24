package com.rently.rentlyAPI.services.auth;

import com.rently.rentlyAPI.entity.auth.RentlyOAuth2User;
import org.springframework.security.oauth2.client.userinfo.DefaultOAuth2UserService;
import org.springframework.security.oauth2.client.userinfo.OAuth2UserRequest;
import org.springframework.security.oauth2.core.OAuth2AuthenticationException;
import org.springframework.security.oauth2.core.user.OAuth2User;
import org.springframework.stereotype.Service;


@Service
public class RentlyOAuth2UserService extends DefaultOAuth2UserService {

	@Override
	public OAuth2User loadUser(OAuth2UserRequest userRequest) throws OAuth2AuthenticationException {
		String provider = userRequest.getClientRegistration().getClientName();
		return new RentlyOAuth2User(super.loadUser(userRequest), provider);
	}

}