package cl.duoc.week2.service;

import java.util.List;

import domain.Pelicula;

public interface IPeliculaService {
    List<Pelicula> findAll();
    Pelicula findById(Long id);
}
