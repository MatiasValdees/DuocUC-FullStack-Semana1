package cl.duoc.week2.repository;

import org.springframework.data.repository.CrudRepository;

import cl.duoc.week2.domain.DirectorEntity;

public interface DirectorRepository extends CrudRepository<DirectorEntity, Long> {
}
