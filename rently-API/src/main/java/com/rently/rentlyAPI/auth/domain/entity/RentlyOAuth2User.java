package com.rently.rentlyAPI.auth.domain.entity;

import lombok.Getter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.oauth2.core.user.OAuth2User;

import java.util.Collection;
import java.util.Map;

@Getter
public class RentlyOAuth2User implements OAuth2User {

	private OAuth2User oAuth2User;
	private String provider;

	public RentlyOAuth2User(OAuth2User oAuth2User, String provider) {
		this.oAuth2User = oAuth2User;
		this.provider = provider;
	}

	@Override
	public <A> A getAttribute(String name) {
		return OAuth2User.super.getAttribute(name);
	}

	@Override
	public Map<String, Object> getAttributes() {
		return oAuth2User.getAttributes();
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		return oAuth2User.getAuthorities();
	}

	@Override
	public String getName() {
		return oAuth2User.getAttribute("name");
	}

	public String getEmail() {
		return oAuth2User.getAttribute("email");
	}

	public String getFirstName() {
		return oAuth2User.getAttribute("given_name");
	}

	public String getLastName() {
		return oAuth2User.getAttribute("family_name");
	}

	public String getPicture() {
		return oAuth2User.getAttribute("picture");
	}

	// Might help determine the user's locale (default language)
	public String getLocale() {
		return oAuth2User.getAttribute("locale");
	}

}
