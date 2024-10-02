 package dev.oscar.personalback.config;

 import org.springframework.context.annotation.Configuration;
 import org.springframework.web.servlet.config.annotation.EnableWebMvc;
 import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
 import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
 
 @Configuration
 @EnableWebMvc // Esta línea es opcional, a menudo se usa en aplicaciones no Spring Boot
 public class WebConfig implements WebMvcConfigurer {
     @Override
     public void addResourceHandlers(ResourceHandlerRegistry registry) {
         registry.addResourceHandler("/upload/**")
                 .addResourceLocations("classpath:/static/upload/");
     }
 }
 