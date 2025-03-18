package cl.duoc.week2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.week2.domain.Pelicula;
import cl.duoc.week2.domain.exceptions.PeliculaNoEncontradaException;
import cl.duoc.week2.repository.IPeliculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PeliculaService implements IPeliculaService{
    private final IPeliculaRepository repository;

    @Override
    public List<Pelicula> findAll() {
        log.info("Buscando todas las peliculas");
        var peliculas=repository.findAll();
        log.info("Cantidad de peliculas encontradas: {}",peliculas.size());
        return peliculas;
    }

    @Override
    public Pelicula findById(Long id) {
        log.info("Buscando pelicula por id: {}",id);
        var pelicula=repository.findById(id);
        if(pelicula.isEmpty()){
            throw new PeliculaNoEncontradaException("id",id.toString());
        }
        return pelicula.get();
    }

}
