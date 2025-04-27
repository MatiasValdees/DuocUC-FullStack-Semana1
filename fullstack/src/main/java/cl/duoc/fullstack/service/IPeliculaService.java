package cl.duoc.fullstack.service;


import cl.duoc.fullstack.domain.PeliculaEntity;
import cl.duoc.fullstack.web.dtos.PeliculaCreateRequest;
import cl.duoc.fullstack.web.dtos.PeliculaUpdateRequest;

import java.util.List;

public interface IPeliculaService {
    List<PeliculaEntity> findAll();
    PeliculaEntity findById(Long id);
    PeliculaEntity save(PeliculaCreateRequest pelicula);
    PeliculaEntity update(PeliculaUpdateRequest request);
    void delete(Long id);
}
