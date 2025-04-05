package cl.duoc.week2.service;

import java.util.List;

import cl.duoc.week2.domain.PeliculaEntity;

public interface IPeliculaService {
    List<PeliculaEntity> findAll();
    PeliculaEntity findById(Long id);
}
