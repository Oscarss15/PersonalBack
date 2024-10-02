package dev.oscar.personalback.config;

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
import org.springframework.web.filter.CorsFilter;

@Configuration
@EnableWebSecurity
public class SecurityConfig {

    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity http) throws Exception {
        http
            .cors(Customizer.withDefaults()) // Habilitar CORS
            .csrf(csrf -> csrf.disable()) // Desactivar CSRF (considerar habilitar en producción)
            .authorizeHttpRequests(authorizeRequests -> 
                authorizeRequests
                    .requestMatchers("/h2-console/**").permitAll() // Permitir acceso a H2 Console
                    .requestMatchers(HttpMethod.GET, "/api/v1/articulos/**").permitAll() // Permitir GET para artículos
                    .requestMatchers(HttpMethod.POST, "/api/v1/articulos/**").permitAll() // Permitir POST para crear artículos
                    .requestMatchers("/upload/**").permitAll() //
                     
                    .anyRequest().authenticated() // Todas las demás solicitudes deben estar autenticadas
            )
            .headers(headers -> headers.frameOptions(frame -> frame.sameOrigin())); // Permitir acceso a H2 Console desde el mismo origen

        return http.build();
    }

    @Bean
    public CorsFilter corsFilter() {
        CorsConfiguration corsConfiguration = new CorsConfiguration();
        corsConfiguration.setAllowCredentials(true); // Permitir credenciales (cookies, headers de autorización, etc.)
        corsConfiguration.addAllowedOrigin("http://localhost:5173"); // Permitir solo este origen (ajusta según tu configuración)
        corsConfiguration.addAllowedHeader("*"); // Permitir todos los headers
        corsConfiguration.addAllowedMethod("*"); // Permitir todos los métodos (GET, POST, PUT, DELETE, etc.)
        
        UrlBasedCorsConfigurationSource source = new UrlBasedCorsConfigurationSource();
        source.registerCorsConfiguration("/**", corsConfiguration); // Aplica a todas las rutas
        return new CorsFilter(source);
    }
}