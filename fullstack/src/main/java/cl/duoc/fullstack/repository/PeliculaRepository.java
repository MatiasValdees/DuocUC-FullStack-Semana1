package cl.duoc.fullstack.repository;

import cl.duoc.fullstack.domain.PeliculaEntity;
import org.springframework.data.repository.CrudRepository;

public interface PeliculaRepository extends CrudRepository<PeliculaEntity, Long> {
}
