package com.rently.rentlyAPI.security.config;

import com.rently.rentlyAPI.security.filter.JwtAuthenticationFilter;
import com.rently.rentlyAPI.auth.services.RentlyOAuth2UserService;
import com.rently.rentlyAPI.security.utils.OAuth2LoginSuccessHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.UsernamePasswordAuthenticationFilter;
import org.springframework.security.web.authentication.logout.LogoutHandler;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

import java.util.Arrays;
import java.util.Collections;

import static com.rently.rentlyAPI.security.Permission.*;
import static com.rently.rentlyAPI.security.Role.ADMIN;
import static com.rently.rentlyAPI.security.Role.COMPANY;
import static org.springframework.http.HttpMethod.*;
import static org.springframework.security.config.http.SessionCreationPolicy.STATELESS;

@Configuration
@EnableWebSecurity
@RequiredArgsConstructor
//@EnableMethodSecurity //Enables @PreAuthroize annotation
public class WebSecurityConfig {
    
    @Autowired
    private RentlyOAuth2UserService oAuth2UserService;
    
    @Autowired
    private OAuth2LoginSuccessHandler oAuth2LoginSuccessHandler;
    private final JwtAuthenticationFilter jwtAuthFilter;
    private final AuthenticationProvider authenticationProvider;
    private final LogoutHandler logoutHandler;
    private static final String[] WHITE_LIST_URL = {
            "http://localhost:8080/api/v1/auth/**",
            "/api/v1/auth/**",
//            "/login/oauth2/code/google",
            "/v2/api-docs",
            "/v3/api-docs",
            "/v3/api-docs/**",
            "/swagger-resources",
            "/swagger-resources/**",
            "/configuration/ui",
            "/configuration/security",
            "/swagger-ui/**",
            "/webjars/**",
            "/swagger-ui.html"};


    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .cors(cors -> cors.configurationSource(corsConfigurationSource()))
                .csrf(AbstractHttpConfigurer::disable)
                .authorizeHttpRequests(req ->
                        req.requestMatchers(WHITE_LIST_URL)
                                .permitAll()
                                .requestMatchers("/api/v1/company/**").hasAnyRole(ADMIN.name(), COMPANY.name())
                                .requestMatchers(GET, "/api/v1/company/**").hasAnyAuthority(ADMIN_READ.name(), COMPANY_READ.name())
                                .requestMatchers(POST, "/api/v1/company/**").hasAnyAuthority(ADMIN_CREATE.name(), COMPANY_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/company/**").hasAnyAuthority(ADMIN_UPDATE.name(), COMPANY_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/company/**").hasAnyAuthority(ADMIN_DELETE.name(), COMPANY_DELETE.name())

                                .requestMatchers("/api/v1/admin/**").hasRole(ADMIN.name())

                                .requestMatchers(GET, "/api/v1/admin/**").hasAuthority(ADMIN_READ.name())
                                .requestMatchers(POST, "/api/v1/admin/**").hasAuthority(ADMIN_CREATE.name())
                                .requestMatchers(PUT, "/api/v1/admin/**").hasAuthority(ADMIN_UPDATE.name())
                                .requestMatchers(DELETE, "/api/v1/admin/**").hasAuthority(ADMIN_DELETE.name())

                                .anyRequest()
                                .authenticated()
                )
//                .oauth2Login(oauth2Login -> oauth2Login
////                    TODO: Do we need this (below)?
////                    .loginPage("http://localhost:5173/login")
////                    TODO: change sucess redirect endpoint
////                    .defaultSuccessUrl("http://localhost:5173/register")
//                    .failureUrl("/api/v1/auth/login-failure")
//                    .successHandler(oAuth2LoginSuccessHandler)
//                    .userInfoEndpoint(userInfo -> userInfo.userService(oAuth2UserService))
//                )
                .sessionManagement(session -> session.sessionCreationPolicy(STATELESS))
                .authenticationProvider(authenticationProvider)
                .addFilterBefore(jwtAuthFilter, UsernamePasswordAuthenticationFilter.class)
                .logout(logout ->
                        logout.logoutUrl("/api/v1/auth/logout")
                                .addLogoutHandler(logoutHandler)
                                .logoutSuccessHandler((request, response, authentication) -> SecurityContextHolder.clearContext())
                )

        ;

        return http.build();
    }

    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(Arrays.asList("http://localhost:8080", "http://localhost:3000", "http://localhost:5173"));
        configuration.setAllowedMethods(Arrays.asList("GET", "POST", "PUT", "DELETE", "OPTIONS", "PATCH"));
        configuration.setAllowedHeaders(Arrays.asList("Authorization", "Content-Type", "Access-Control-Allow-Headers", "X-Requested-With"));
        configuration.setExposedHeaders(Collections.singletonList("Authorization"));
        configuration.setAllowCredentials(true);

        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }


}
