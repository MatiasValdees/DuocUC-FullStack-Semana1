package cl.duoc.week2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.week2.domain.PeliculaEntity;
import cl.duoc.week2.domain.exceptions.PeliculaNoEncontradaException;
import cl.duoc.week2.repository.PeliculaRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PeliculaService implements IPeliculaService{
    private final PeliculaRepository repository;

    @Override
    public List<PeliculaEntity> findAll() {
        log.info("Buscando todas las peliculas");
        var peliculas=(List<PeliculaEntity>)repository.findAll();
        log.info("Cantidad de peliculas encontradas: {}",peliculas.size());
        return peliculas;
    }

    @Override
    public PeliculaEntity findById(Long id) {
        log.info("Buscando pelicula por id: {}",id);
        var pelicula=repository.findById(id);
        if(pelicula.isEmpty()){
            throw new PeliculaNoEncontradaException("id",id.toString());
        }
        return pelicula.get();
    }

}
