package cl.duoc.week2.service;

import java.util.List;

import org.springframework.stereotype.Service;

import cl.duoc.week2.domain.PeliculaEntity;
import cl.duoc.week2.domain.exceptions.DirectorNoEncontrado;
import cl.duoc.week2.domain.exceptions.GeneroNoEncontradoException;
import cl.duoc.week2.domain.exceptions.PeliculaNoEncontradaException;
import cl.duoc.week2.repository.DirectorRepository;
import cl.duoc.week2.repository.GeneroRepository;
import cl.duoc.week2.repository.PeliculaRepository;
import cl.duoc.week2.web.dtos.PeliculaCreateRequest;
import cl.duoc.week2.web.dtos.PeliculaUpdateRequest;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@RequiredArgsConstructor
@Slf4j
@Service
public class PeliculaService implements IPeliculaService{
    private final PeliculaRepository repository;
    private final GeneroRepository generoRepository;
    private final DirectorRepository directorRepository;

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

    @Override
    public PeliculaEntity save(PeliculaCreateRequest request) {
        log.info("Guardando pelicula: {}",request);
        var entity=new PeliculaEntity();
        setEntities(entity,request.directorId(), request.generoId());
        entity.setTitulo(request.titulo());
        entity.setAnnio(request.annio());
        entity.setSinopsis(request.sinopsis());
        return repository.save(entity);
    }

    @Override
    public PeliculaEntity update(PeliculaUpdateRequest request) {
        log.info("Actualizando pelicula: {}",request.id());
        var entity=repository.findById(request.id()).orElseThrow(()->new PeliculaNoEncontradaException("id",request.id().toString()));
        entity.setTitulo(request.titulo());
        entity.setAnnio(request.annio());
        entity.setSinopsis(request.sinopsis());
        setEntities(entity,request.directorId(), request.generoId());
        return repository.save(entity);
    }

    @Override
    public void delete(Long id) {
        log.info("Eliminando pelicula por id: {}",id);
        var pelicula=repository.findById(id).orElseThrow(()->new PeliculaNoEncontradaException("id",id.toString()));
        log.info("Pelicula encontrada: {}",pelicula);
        repository.delete(pelicula);
        log.info("Pelicula eliminada: {}",pelicula);
    }
    
    private void setEntities(PeliculaEntity entity, Long directorId,Long generoId) {
        log.info("Buscando genero por id: {}",generoId);
        var genero=generoRepository.findById(generoId).orElseThrow(()->new GeneroNoEncontradoException("id",generoId.toString()));
        log.info("Genero encontrado: {}",genero);
        log.info("Buscando director por id: {}",directorId);
        var director=directorRepository.findById(directorId).orElseThrow(()->new DirectorNoEncontrado("id",directorId.toString()));
        log.info("Director encontrado: {}",director);
        entity.setGenero(genero);
        entity.setDirector(director);
        log.info("Genero y director asignados a la pelicula");
    }


}
