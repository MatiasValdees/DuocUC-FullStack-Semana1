package cl.duoc.week2.service;

import java.util.List;

import cl.duoc.week2.domain.PeliculaEntity;
import cl.duoc.week2.web.dtos.PeliculaCreateRequest;
import cl.duoc.week2.web.dtos.PeliculaUpdateRequest;

public interface IPeliculaService {
    List<PeliculaEntity> findAll();
    PeliculaEntity findById(Long id);
    PeliculaEntity save(PeliculaCreateRequest pelicula);
    PeliculaEntity update(PeliculaUpdateRequest request);
    void delete(Long id);
}
