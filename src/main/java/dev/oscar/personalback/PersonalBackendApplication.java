package dev.oscar.personalback;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@SpringBootApplication
@EntityScan(basePackages = {
    "dev.oscar.personalback.articulos.model", 
    "dev.oscar.personalback.salas.model" 
})
@EnableJpaRepositories(basePackages = {
    "dev.oscar.personalback.articulos.repository", 
    "dev.oscar.personalback.salas.repository" 
})
public class PersonalBackendApplication {
    public static void main(String[] args) {
        SpringApplication.run(PersonalBackendApplication.class, args);
    }
}