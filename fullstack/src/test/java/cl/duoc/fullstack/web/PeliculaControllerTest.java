package cl.duoc.fullstack.web;

import cl.duoc.fullstack.mocks.PeliculaMock;
import cl.duoc.fullstack.service.PeliculaService;
import cl.duoc.fullstack.web.dtos.PeliculaUpdateRequest;
import cl.duoc.fullstack.web.hateoas.PeliculaModelAssembler;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.context.annotation.Import;
import org.springframework.http.MediaType;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;

import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doNothing;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

@Import(PeliculaModelAssembler.class)
@WebMvcTest(PeliculaController.class)
public class PeliculaControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @MockitoBean
    private PeliculaService peliculaService;

    private ObjectMapper mapper;

    @BeforeEach
    void setUp() {
        mapper = new ObjectMapper();
    }

    @Test
    void testCreate() throws Exception {
        //Given
        when(peliculaService.save(any())).thenReturn(PeliculaMock.Pelicula1);
        //When
        mockMvc.perform(post("/peliculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(PeliculaMock.peliculaRequest))
                )
                .andExpect(status().isCreated())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.titulo").value("El Padrino"))
                .andExpect(jsonPath("$.data.año").value(1972))
                .andExpect(jsonPath("$.data.genero").value("Acción"))
                .andExpect(jsonPath("$.data.director").value("Francis Ford Coppola"));
    }

    @Test
    void testDelete() throws Exception {
        //Given
        doNothing().when(peliculaService).delete(1L);
        //When
        mockMvc.perform(delete("/peliculas/{id}", 1L))
        //Then
                .andExpect(status().isNoContent())
                .andExpect(jsonPath("$.status").value("OK"))
        ;
    }

    @Test
    void testGetAll() throws Exception {
        when(peliculaService.findAll()).thenReturn(List.of(PeliculaMock.Pelicula1,PeliculaMock.pelicula2));
        //When
        mockMvc.perform(get("/peliculas")
                        .contentType(MediaType.APPLICATION_JSON)
                )
        //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data.content").isArray())
                .andExpect(jsonPath("$.data.content",hasSize(2)))
                .andExpect(jsonPath("$.data.content[0].id").value(1L))
                //Testear Response completo
        // .andExpect(content().json(mapper.writeValueAsString()))
        ;
    }

    @Test
    void testGetById() throws Exception {
        //Given
        when(peliculaService.findById(1L)).thenReturn(PeliculaMock.Pelicula1);
        //When
        mockMvc.perform(get("/peliculas/{id}", 1L)
                        .accept(MediaType.APPLICATION_JSON)
                )
        //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.titulo").value("El Padrino"))
                .andExpect(jsonPath("$.data.año").value(1972))
                .andExpect(jsonPath("$.data.genero").value("Acción"))
        ;
    }

    @Test
    void testUpdate() throws Exception  {
        PeliculaUpdateRequest peliculaUpdateRequest = new PeliculaUpdateRequest(
                1L,
                "El Padrino",
                1972,
                1L,
                1L,
                "La historia de una familia mafiosa en los Estados Unidos."
        );
        //Given
        when(peliculaService.update(any())).thenReturn(PeliculaMock.Pelicula1);
        //When
        mockMvc.perform(put("/peliculas")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(mapper.writeValueAsString(peliculaUpdateRequest))
                )
        //Then
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON))
                .andExpect(jsonPath("$.status").value("OK"))
                .andExpect(jsonPath("$.data.id").value(1L))
                .andExpect(jsonPath("$.data.titulo").value("El Padrino"))
                .andExpect(jsonPath("$.data.año").value(1972))
                .andExpect(jsonPath("$.data.genero").value("Acción"))
                .andExpect(jsonPath("$.data.director").value("Francis Ford Coppola"));
    }
}
