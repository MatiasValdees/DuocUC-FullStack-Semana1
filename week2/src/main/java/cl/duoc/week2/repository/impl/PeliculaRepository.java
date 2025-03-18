package cl.duoc.week2.repository.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Repository;

import cl.duoc.week2.repository.IPeliculaRepository;
import cl.duoc.week2.repository.data.Peliculas;
import domain.Pelicula;

@Repository
public class PeliculaRepository implements IPeliculaRepository{

    @Override
    public List<Pelicula> findAll(){
        return Peliculas.peliculas;
    };

    @Override
    public Optional<Pelicula> findById(Long id){
        return Peliculas.peliculas.stream().filter(p->p.getId().equals(id)).findFirst();
    };
}
