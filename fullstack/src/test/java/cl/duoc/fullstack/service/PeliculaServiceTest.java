package cl.duoc.fullstack.service;

import cl.duoc.fullstack.domain.DirectorEntity;
import cl.duoc.fullstack.domain.GeneroEntity;
import cl.duoc.fullstack.domain.PeliculaEntity;
import cl.duoc.fullstack.domain.exceptions.DirectorNoEncontrado;
import cl.duoc.fullstack.domain.exceptions.GeneroNoEncontradoException;
import cl.duoc.fullstack.domain.exceptions.PeliculaNoEncontradaException;
import cl.duoc.fullstack.mocks.GeneroMock;
import cl.duoc.fullstack.mocks.PeliculaMock;
import cl.duoc.fullstack.repository.DirectorRepository;
import cl.duoc.fullstack.repository.GeneroRepository;
import cl.duoc.fullstack.repository.PeliculaRepository;
import cl.duoc.fullstack.web.dtos.PeliculaUpdateRequest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class PeliculaServiceTest {

    @Mock
    private PeliculaRepository repository;
    @Mock
    private GeneroRepository generoRepository;
    @Mock
    private DirectorRepository directorRepository;
    @InjectMocks
    private PeliculaService service;

    @Test
    @DisplayName("Test de eliminación de película exitoso")
    void testDelete() {
        when(repository.findById(1L)).thenReturn(Optional.of(PeliculaMock.Pelicula1));
        doNothing().when(repository).delete(PeliculaMock.Pelicula1);
        service.delete(1L);
    }
    @Test
    @DisplayName("Test de eliminación de película - Pelicula no encontrada")
    void testDeletePeliculaNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PeliculaNoEncontradaException.class, () -> service.delete(1L));
        verify(repository, never()).delete(any());
    }

    @Test
    void testFindAll() {
        PeliculaEntity pelicula2 = PeliculaEntity.builder()
                .id(1L)
                .titulo("El Padrino")
                .annio(1972)
                .genero(new GeneroEntity(1L, "Drama"))
                .director(new DirectorEntity(1L, "Francis Ford Coppola"))
                .sinopsis("La historia de una familia mafiosa en los Estados Unidos.")
                .build();
        List<PeliculaEntity>list= List.of(PeliculaMock.Pelicula1, pelicula2);
        when(repository.findAll()).thenReturn(list);
        var result = service.findAll();
        assertEquals(2, result.size());
    }

    @Test
    @DisplayName("Test de búsqueda de película por Id")
    void testFindById() {
        when(repository.findById(1L)).thenReturn(Optional.of(PeliculaMock.Pelicula1));
        var result=service.findById(1L);
        assertNotNull(result);
    }
    @Test
    @DisplayName("Test de búsqueda de película por Id - Película no encontrada")
    void testFindByIdNotFound() {
        when(repository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(PeliculaNoEncontradaException.class, () -> service.findById(1L));
    }

    @Test
    @DisplayName("Test de Guardar película exitoso")
    void testSave() {
        when(generoRepository.findById(1L)).thenReturn(Optional.ofNullable(GeneroMock.Genero1));
        when(directorRepository.findById(1L)).thenReturn(Optional.ofNullable(PeliculaMock.Pelicula1.getDirector()));
        when(repository.save(any())).thenReturn(PeliculaMock.Pelicula1);
        var result=service.save(PeliculaMock.peliculaRequest);
        assertNotNull(result);
        assertEquals(PeliculaMock.Pelicula1.getId(), result.getId());
        verify(generoRepository)
                .findById(PeliculaMock.peliculaRequest.generoId());
        verify(directorRepository)
                .findById(PeliculaMock.peliculaRequest.directorId());
    }
    @Test
    @DisplayName("Test de Guardar película-Genero no encontrado")
    void testSaveGeneroNotFound() {
        when(generoRepository.findById(1L)).thenReturn(Optional.empty());
        assertThrows(GeneroNoEncontradoException.class, () -> service.save(PeliculaMock.peliculaRequest));
    }

    @Test
    @DisplayName("Test de Guardar película-Director no encontrado")
    void testSaveDirectorNotFound() {
        when(directorRepository.findById(1L)).thenReturn(Optional.empty());
        when(generoRepository.findById(1L)).thenReturn(Optional.ofNullable(GeneroMock.Genero1));
        assertThrows(DirectorNoEncontrado.class, () -> service.save(PeliculaMock.peliculaRequest));
    }

    @Test
    @DisplayName("Test de actualización de película exitoso")
    void testUpdate() {
        final PeliculaUpdateRequest peliculaUpdateRequest = new PeliculaUpdateRequest(
                2L,
                "El Padrino- Parte II",
                1975,
                1L,
                1L,
                "La historia de una familia mafiosa en los Estados Unidos 2."
        );
        when(repository.findById(2L)).thenReturn(Optional.ofNullable(PeliculaMock.pelicula2));
        when(generoRepository.findById(1L)).thenReturn(Optional.ofNullable(GeneroMock.Genero1));
        when(directorRepository.findById(1L)).thenReturn(Optional.ofNullable(PeliculaMock.Pelicula1.getDirector()));
        when(repository.save(PeliculaMock.pelicula2)).thenReturn(PeliculaMock.pelicula2);
        var result=service.update(peliculaUpdateRequest);
        assertNotNull(result);
        assertEquals(PeliculaMock.pelicula2.getId(), result.getId());
        assertEquals(peliculaUpdateRequest.titulo(), result.getTitulo());
        assertEquals(peliculaUpdateRequest.annio(), result.getAnnio());
        assertEquals(peliculaUpdateRequest.generoId(), result.getGenero().getId());
        assertEquals(peliculaUpdateRequest.directorId(), result.getDirector().getId());
        assertEquals(peliculaUpdateRequest.sinopsis(), result.getSinopsis());
        verify(generoRepository,only())
                .findById(peliculaUpdateRequest.generoId());
    }

    @Test
    @DisplayName("Test de actualización de película- Película no encontrada")
    void testUpdatePeliculaNotFound() {
        final PeliculaUpdateRequest peliculaUpdateRequest = new PeliculaUpdateRequest(
                2L,
                "El Padrino- Parte II",
                1975,
                1L,
                1L,
                "La historia de una familia mafiosa en los Estados Unidos 2."
        );
        when(repository.findById(2L)).thenReturn(Optional.empty());
        assertThrows(PeliculaNoEncontradaException.class, () -> service.update(peliculaUpdateRequest));
        
        verify(generoRepository,never())
                .findById(peliculaUpdateRequest.generoId());
        verify(directorRepository,never())
                .findById(peliculaUpdateRequest.directorId());
        verify(repository,never())
                .save(PeliculaMock.pelicula2);

    }


}
