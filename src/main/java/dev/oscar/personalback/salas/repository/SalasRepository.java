 package dev.oscar.personalback.salas.repository;



import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import dev.oscar.personalback.salas.model.Salas;


@Repository
public interface SalasRepository extends JpaRepository<Salas, Long> {
    
} 