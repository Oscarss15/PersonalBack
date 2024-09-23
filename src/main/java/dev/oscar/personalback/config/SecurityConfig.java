package dev.oscar.personalback.config;

import java.util.Arrays;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;
import org.springframework.web.cors.CorsConfigurationSource;
import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;


@Configuration
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults())
            
            .csrf(csrf->csrf.disable())
            .authorizeHttpRequests(authorizeRequests ->
                authorizeRequests
                    .requestMatchers("/h2-console/**").permitAll()
                     .requestMatchers(HttpMethod.GET, "/api/v1/articulos/**").permitAll() 
                    .requestMatchers("/api/v1/articulos/**").permitAll() 
                    .anyRequest().authenticated() 
            )
             .headers(headers -> headers.frameOptions(frame->frame.sameOrigin()));
             http.headers(header -> header.frameOptions(frame -> frame.sameOrigin())); 

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); // Permitir credenciales (cookies, headers de autorización, etc.)
        corsConfiguration.addAllowedOrigin("http://localhost:5173"); // Permite solo este origen
        corsConfiguration.addAllowedHeader("*"); // Permite todos los headers
        corsConfiguration.addAllowedMethod("*"); // Permite todos los métodos (GET, POST, PUT, DELETE, etc.)
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // Aplica a todas las rutas
        return new CorsFilter(source);
    }
}