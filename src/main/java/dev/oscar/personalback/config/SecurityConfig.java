package dev.oscar.personalback.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.web.cors.CorsConfiguration;

import org.springframework.web.cors.UrlBasedCorsConfigurationSource;
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) 
            .csrf(csrf -> csrf.disable()) 
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/h2-console/**").permitAll() 
                    .requestMatchers(HttpMethod.GET, "/api/v1/articulos/**").permitAll() 
                    .requestMatchers(HttpMethod.POST, "/api/v1/articulos/**").permitAll() 
                    .requestMatchers("/upload/**").permitAll() //
                     
                 
                    .anyRequest().permitAll()
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); 

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); 
        corsConfiguration.addAllowedOrigin("http://localhost:5173"); 
        corsConfiguration.addAllowedHeader("*"); 
        corsConfiguration.addAllowedMethod("*"); 
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); 
        return new CorsFilter(source);
    }
}