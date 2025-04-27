package cl.duoc.fullstack.repository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
class DirectorRepositoryTest {
    @Autowired
    private DirectorRepository directorRepository;

    @Test
    @DisplayName("Test to find a director by ID")
    void testFindById() {
        // Given
        Long directorId = 1L;
        // When
        var director = directorRepository.findById(directorId);
        // Then
        assertTrue(director.isPresent());
        assertEquals(directorId, director.get().getId());
        assertEquals("Steven Spielberg", director.get().getNombre());
    }
}