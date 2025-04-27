package cl.duoc.fullstack.repository;

import cl.duoc.fullstack.domain.DirectorEntity;
import org.springframework.data.repository.CrudRepository;

public interface DirectorRepository extends CrudRepository<DirectorEntity, Long> {
}
