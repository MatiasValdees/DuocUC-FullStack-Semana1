package cl.duoc.week2.repository;

import org.springframework.data.repository.CrudRepository;

import cl.duoc.week2.domain.PeliculaEntity;

public interface PeliculaRepository extends CrudRepository<PeliculaEntity, Long> {
}
