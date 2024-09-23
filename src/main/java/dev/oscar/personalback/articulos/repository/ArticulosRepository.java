package dev.oscar.personalback.articulos.repository;


import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import java.util.List;
import dev.oscar.personalback.articulos.model.Articulos;


@Repository
public interface ArticulosRepository extends JpaRepository<Articulos, Long> {
    List<Articulos> findByTipo(String tipo);
}