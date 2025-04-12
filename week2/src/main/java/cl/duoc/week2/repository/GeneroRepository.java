package cl.duoc.week2.repository;

import org.springframework.data.repository.CrudRepository;

import cl.duoc.week2.domain.GeneroEntity;

public interface GeneroRepository extends CrudRepository<GeneroEntity, Long> {
    // Aquí puedes agregar métodos personalizados si es necesario
    // Por ejemplo, buscar por nombre de género
    // List<GeneroEntity> findByNombre(String nombre);

}
