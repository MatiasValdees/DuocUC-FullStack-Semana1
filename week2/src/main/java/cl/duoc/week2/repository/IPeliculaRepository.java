package cl.duoc.week2.repository;

import java.util.List;
import java.util.Optional;

import domain.Pelicula;

public interface IPeliculaRepository {
    List<Pelicula> findAll();
    Optional<Pelicula> findById(Long id);
}
