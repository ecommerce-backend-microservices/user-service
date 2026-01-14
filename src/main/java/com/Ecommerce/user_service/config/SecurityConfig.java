package com.Ecommerce.user_service.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
public class SecurityConfig {

    @Bean
    public PasswordEncoder passwordEncoder() {
        return new BCryptPasswordEncoder();
    }

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {

        http
                // ❌ Disable CSRF (we are building REST APIs)
                .csrf(csrf -> csrf.disable())

                // ❌ Disable form login & basic auth UI
                .httpBasic(Customizer.withDefaults())
                .formLogin(form -> form.disable())

                // ✅ Authorization rules
                .authorizeHttpRequests(auth -> auth
                        // PUBLIC APIs
                        .requestMatchers(HttpMethod.POST, "/api/users").permitAll()
                        .requestMatchers(HttpMethod.POST, "/api/users/login").permitAll()
                        .requestMatchers("/actuator/**").permitAll()

                        // EVERYTHING ELSE NEEDS AUTH (later)
                        .anyRequest().authenticated()
                );

        return http.build();
    }
}
