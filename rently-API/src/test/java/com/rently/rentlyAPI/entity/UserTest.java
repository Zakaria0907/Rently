//package com.rently.rentlyAPI.entity;
//
//import com.rently.rentlyAPI.auth.entity.AccessToken;
//import com.rently.rentlyAPI.auth.entity.enums.Provider;
//import com.rently.rentlyAPI.entity.user.User;
//import com.rently.rentlyAPI.security.Role;
//import org.junit.jupiter.api.Test;
//
//import java.util.List;
//
//import static org.mockito.Mockito.mock;
//import static org.junit.jupiter.api.Assertions.*;
//
//public class UserTest {
//
//    S3File mockedProfilePicture = mock(S3File.class);
//    List<AccessToken> mockedAccessTokens = mock(List.class);
//    List<Condo> mockedCondos = mock(List.class);
//    List<Key> mockedKeys = mock(List.class);
//    List<Building> mockedBuildings = mock(List.class);
//
//    @Test
//    public void testAllArgsConstructor() {
//        // Arrange
//
//        // Act
//        User testUser = new User("test@example.com", "password", "John", "Doe", "1234567890", "Bio", mockedProfilePicture, Provider.RENTLY, Role.COMPANY, mockedAccessTokens, mockedCondos, mockedKeys, mockedBuildings);
//
//        // Assert
//        assertEquals("test@example.com", testUser.getEmail());
//        assertEquals("password", testUser.getPassword());
//        assertEquals("John", testUser.getFirstName());
//        assertEquals("Doe", testUser.getLastName());
//        assertEquals("1234567890", testUser.getPhoneNumber());
//        assertEquals("Bio", testUser.getBio());
//        assertEquals(mockedProfilePicture, testUser.getProfilePicture());
//        assertEquals(Provider.RENTLY, testUser.getProvider());
//        assertEquals(Role.COMPANY, testUser.getRole());
//        assertEquals(mockedAccessTokens, testUser.getAccessTokens());
//        assertEquals(mockedCondos, testUser.getCondos());
//        assertEquals(mockedKeys, testUser.getRegistrationKey());
//        assertEquals(mockedBuildings, testUser.getBuildings());
//        assertEquals("test@example.com", testUser.getUsername());
//        assertTrue(testUser.isAccountNonExpired());
//        assertTrue(testUser.isAccountNonLocked());
//        assertTrue(testUser.isCredentialsNonExpired());
//        assertTrue(testUser.isEnabled());
//    }
//
//    @Test
//    public void testNoArgsConstructor() {
//        // Act
//        User testUser = new User();
//
//        // Assert
//        assertNull(testUser.getEmail());
//        assertNull(testUser.getPassword());
//        assertNull(testUser.getFirstName());
//        assertNull(testUser.getLastName());
//        assertNull(testUser.getPhoneNumber());
//        assertNull(testUser.getBio());
//        assertNull(testUser.getProfilePicture());
//        assertNull(testUser.getProvider());
//        assertNull(testUser.getRole());
//        assertNull(testUser.getAccessTokens());
//        assertNull(testUser.getCondos());
//        assertNull(testUser.getRegistrationKey());
//        assertNull(testUser.getBuildings());
//        assertNull(testUser.getUsername());
//        assertTrue(testUser.isAccountNonExpired());
//        assertTrue(testUser.isAccountNonLocked());
//        assertTrue(testUser.isCredentialsNonExpired());
//        assertTrue(testUser.isEnabled());
//    }
//
//    @Test
//    public void testBuilder() {
//        // Act
//        User testUser = User.builder().build();
//
//        // Assert
//        assertNull(testUser.getEmail());
//        assertNull(testUser.getPassword());
//        assertNull(testUser.getFirstName());
//        assertNull(testUser.getLastName());
//        assertNull(testUser.getPhoneNumber());
//        assertNull(testUser.getBio());
//        assertNull(testUser.getProfilePicture());
//        assertNull(testUser.getProvider());
//        assertNull(testUser.getRole());
//        assertNull(testUser.getAccessTokens());
//        assertNull(testUser.getCondos());
//        assertNull(testUser.getRegistrationKey());
//        assertNull(testUser.getBuildings());
//        assertNull(testUser.getUsername());
//        assertTrue(testUser.isAccountNonExpired());
//        assertTrue(testUser.isAccountNonLocked());
//        assertTrue(testUser.isCredentialsNonExpired());
//        assertTrue(testUser.isEnabled());
//    }
//
//}
