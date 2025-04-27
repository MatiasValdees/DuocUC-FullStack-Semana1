package cl.duoc.fullstack.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class GeneroRepositoryTest {
    @Autowired
    private GeneroRepository generoRepository;

    @Test
    @DisplayName("Test to find a Genero by Id")
    void testFindById() {
        // When
         var genero=generoRepository.findById(1L);
        // Then
        assertTrue(genero.isPresent());
        assertEquals(1L,genero.get().getId());
    }

}