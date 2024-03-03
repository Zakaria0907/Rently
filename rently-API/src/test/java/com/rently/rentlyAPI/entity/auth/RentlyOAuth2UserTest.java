package com.rently.rentlyAPI.entity.auth;

import org.junit.jupiter.api.Test;
import org.springframework.security.oauth2.core.user.OAuth2User;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.mock;
public class RentlyOAuth2UserTest {

    OAuth2User mockedOAuth2User = mock(OAuth2User.class);
    String provider = "bing";

    @Test
    public void testRentlyOAuth2User() {
        // Arrange


        // Act
        RentlyOAuth2User testRentlyOAuth2User = new RentlyOAuth2User(mockedOAuth2User, provider);

        // Assert
        assertEquals(mockedOAuth2User, testRentlyOAuth2User.getOAuth2User());
        assertEquals(provider, testRentlyOAuth2User.getProvider());
        assertEquals(mockedOAuth2User.getAttributes(), testRentlyOAuth2User.getAttributes());
        assertEquals(mockedOAuth2User.getAuthorities(), testRentlyOAuth2User.getAuthorities());
        assertEquals(mockedOAuth2User.getAttribute("name"), testRentlyOAuth2User.getName());
        assertEquals(mockedOAuth2User.getAttribute("email"), testRentlyOAuth2User.getEmail());
        assertEquals(mockedOAuth2User.getAttribute("given_name"), testRentlyOAuth2User.getFirstName());
        assertEquals(mockedOAuth2User.getAttribute("family_name"), testRentlyOAuth2User.getLastName());
        assertEquals(mockedOAuth2User.getAttribute("picture"), testRentlyOAuth2User.getPicture());
        assertEquals(mockedOAuth2User.getAttribute("locale"), testRentlyOAuth2User.getLocale());
    }
}
