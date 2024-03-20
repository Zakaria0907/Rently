//package com.rently.rentlyAPI.security.config;
//
//import com.rently.rentlyAPI.entity.User.User;
//import com.rently.rentlyAPI.repository.UserRepository;
//import org.junit.jupiter.api.Test;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//import org.springframework.security.core.userdetails.UserDetails;
//import org.springframework.security.core.userdetails.UserDetailsService;
//import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
//
//import java.util.Optional;
//
//import static org.junit.jupiter.api.Assertions.assertEquals;
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//public class AuthInfrastructureConfigTest {
//
//    UserRepository mockedUserRepository = mock(UserRepository.class);
//    User mockedUser = mock(User.class);
//    DaoAuthenticationProvider mockedDaoAuthenticationProvider = mock(DaoAuthenticationProvider.class);
//
//    @Test
//    public void testUserDetailsService() {
//        // Arrange
//        String testString = "bingbong@gmail.com";
//        AuthInfrastructureConfig testAuthInfrastructureConfig = new AuthInfrastructureConfig(mockedUserRepository);
//        when(mockedUserRepository.findByEmail(testString)).thenReturn(Optional.of(mockedUser));
//
//
//        // Act
//        UserDetailsService testUserDetailsService = testAuthInfrastructureConfig.userDetailsService();
//        UserDetails testUserDetails = testUserDetailsService.loadUserByUsername("bingbong@gmail.com");
//
//        // Assert
//        assertEquals(mockedUser, testUserDetails);
//
//    }
//
//    @Test
//    public void testAuthenticationProvider() {
//        // Arrange
//        String testString = "bingbong@gmail.com";
//        AuthInfrastructureConfig testAuthInfrastructureConfig = new AuthInfrastructureConfig(mockedUserRepository);
//        when(mockedUserRepository.findByEmail(testString)).thenReturn(Optional.of(mockedUser));
//        when(passwordEncoder)
//
//        // Act
//        UserDetailsService testUserDetailsService = testAuthInfrastructureConfig.userDetailsService();
//        UserDetails testUserDetails = testUserDetailsService.loadUserByUsername("bingbong@gmail.com");
//
//        // Assert
//        assertEquals(mockedUser, testUserDetails);
//
//    }
//
//
//}
