package cl.duoc.fullstack.repository;

import cl.duoc.fullstack.domain.PeliculaEntity;
import cl.duoc.fullstack.mocks.PeliculaMock;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class PeliculaRepositoryTest {
    @Autowired
    private PeliculaRepository peliculaRepository;

    @Test
    @DisplayName("Test to find a Pelicula by Id")
    void testFindById() {
        // Given
        Long peliculaId = 1L;
        // When
        var pelicula = peliculaRepository.findById(peliculaId);
        // Then
        assertTrue(pelicula.isPresent());
        assertEquals(peliculaId, pelicula.get().getId());
    }

    @Test
    @DisplayName("Test to find all Pelicuas")
    void testFindAll() {
        // Given
        // When
        var peliculas = peliculaRepository.findAll();
        // Then
        assertFalse(((List<PeliculaEntity>)peliculas).isEmpty());
    }

    @Test
    @DisplayName("Test Create Pelicula")
    void testCreate() {
        // Given
        var pelicula= PeliculaMock.Pelicula10;
        // When
        PeliculaEntity peliculaToPersist = peliculaRepository.save(pelicula);
        //Se puede agregar un metodo de busqueda por otro atributo y buscarlo
        //Si se busca por id no usa el query, ya que, la persistencia se encuentra en el contexto de persistencia
        // Then
        assertNotNull(peliculaToPersist);
        assertNotNull(peliculaToPersist.getId());
    }

    @Test
    @DisplayName("Test Delete Pelicula")
    void testDelete() {
        // Given
        var pelicula= peliculaRepository.findById(1L);
        // When
        peliculaRepository.delete(pelicula.get());
        // Then
        assertThrows(NoSuchElementException.class, () -> {
            peliculaRepository.findById(1L).orElseThrow();
        });
    }

}