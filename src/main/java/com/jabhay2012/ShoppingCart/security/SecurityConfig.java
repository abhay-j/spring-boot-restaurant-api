package com.jabhay2012.ShoppingCart.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.http.SessionCreationPolicy;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.InMemoryUserDetailsManager;
import org.springframework.security.provisioning.JdbcUserDetailsManager;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.core.userdetails.User;

@Configuration
@EnableWebSecurity
public class SecurityConfig {
    @Autowired
    private CustomUserDetailsService userDetailsService;
    @Autowired
    private JwtAuthEntryPoint authEntryPoint;

    public SecurityConfig(CustomUserDetailsService userDetailsService, JwtAuthEntryPoint authEntryPoint) {
        this.userDetailsService = userDetailsService;
        this.authEntryPoint = authEntryPoint;
    }

//    @Bean
//    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
//        http
//                .authorizeHttpRequests((requests) -> requests.requestMatchers("/api/auth/**").permitAll()
//                        .anyRequest().authenticated())
//                .sessionManagement(sess -> sess.sessionCreationPolicy(SessionCreationPolicy.STATELESS));
//        // .formLogin(Customizer.withDefaults())
//        // .csrf(csrf -> csrf.disable());
//        http.httpBasic(Customizer.withDefaults());
//        http.csrf(csrf -> csrf.disable());
//
//        return http.build();
//    }
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
                .authorizeHttpRequests((requests) -> requests
                        .requestMatchers("/api/auth/**").permitAll()  // Allow all methods (GET, POST, etc.) to /api/auth/**
                        .anyRequest().authenticated()  // Require authentication for all other requests
                )
                .sessionManagement(sess -> sess
                        .sessionCreationPolicy(SessionCreationPolicy.STATELESS)  // Use stateless session management
                )
                .httpBasic(Customizer.withDefaults())  // Enable HTTP Basic authentication
                .csrf(csrf -> csrf.disable());  // Disable CSRF protection (necessary for stateless APIs)

        return http.build();
    }
//
//    @Bean
//    public UserDetailsService userDetailsService() {
//
//        UserDetails user1 = User.withUsername("user1").password(passwordEncoder().encode("password1")).roles("USER")
//                .build();
//        UserDetails admin1 = User.withUsername("admin1").password(passwordEncoder().encode("adminPass")).roles("ADMIN")
//                .build();
//
//        return new InMemoryUserDetailsManager(user1, admin1);
//
//    }

    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration authenticationConfiguration)
            throws Exception {
        return authenticationConfiguration.getAuthenticationManager();
    }

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

}