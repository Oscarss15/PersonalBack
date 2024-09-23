/* package dev.oscar.personalback.config;

import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

public class WebConfig implements WebMvcConfigurer {
     public void addCorsMappings(CorsRegistry registry) {
        registry.addMapping("/api/v1/**") // Ajusta la ruta según tus necesidades
                .allowedOrigins("*") // Permite todas las fuentes (ajusta según sea necesario)
                .allowedMethods("GET", "POST", "PUT", "DELETE") // Métodos permitidos
                .allowCredentials(true); // Permitir credenciales (cookies, etc.)
    }
    @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
   }
}
 */