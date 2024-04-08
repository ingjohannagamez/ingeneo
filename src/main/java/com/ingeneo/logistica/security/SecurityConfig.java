package com.ingeneo.logistica.security;

import java.util.List;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

	@Bean
	public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
	    http
	        .csrf(csrf -> csrf.disable()) // Deshabilita CSRF, común en APIs REST.
	        .authorizeHttpRequests(auth -> auth
	            .requestMatchers(HttpMethod.GET, "/api/**").authenticated()
	            .requestMatchers(HttpMethod.POST, "/api/**").authenticated()
	            .requestMatchers(HttpMethod.PUT, "/api/**").authenticated()
	            .requestMatchers(HttpMethod.DELETE, "/api/**").authenticated()
	            .anyRequest().permitAll()
	        )
	        .httpBasic(Customizer.withDefaults()); // Utiliza autenticación básica.

	    return http.build();
	}
    
    @Bean
    public CorsConfigurationSource corsConfigurationSource() {
        CorsConfiguration configuration = new CorsConfiguration();
        configuration.setAllowedOrigins(List.of("*")); // Ajusta según sea necesario
        configuration.setAllowedMethods(List.of("GET", "POST", "PUT", "DELETE")); // Ajusta según sea necesario
        configuration.setAllowedHeaders(List.of("*")); // Ajusta según sea necesario
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", configuration);
        return source;
    }
}
