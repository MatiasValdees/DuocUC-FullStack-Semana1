package cl.duoc.fullstack.web;

import cl.duoc.fullstack.domain.PeliculaEntity;
import cl.duoc.fullstack.service.IPeliculaService;
import cl.duoc.fullstack.web.dtos.PeliculaCreateRequest;
import cl.duoc.fullstack.web.dtos.PeliculaResponse;
import cl.duoc.fullstack.web.dtos.PeliculaUpdateRequest;
import cl.duoc.fullstack.web.dtos.ResponseWrapper;
import cl.duoc.fullstack.web.hateoas.PeliculaModelAssembler;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.hateoas.CollectionModel;
import org.springframework.hateoas.EntityModel;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import static org.springframework.hateoas.server.mvc.WebMvcLinkBuilder.*;

import java.time.LocalDateTime;
import java.util.List;



@RestController
@RequestMapping(path = "peliculas")
@RequiredArgsConstructor
public class PeliculaController {
    private final IPeliculaService service;
    private final PeliculaModelAssembler assembler;

    @GetMapping
    public ResponseEntity<ResponseWrapper<CollectionModel<EntityModel<PeliculaResponse>>>> getAll() {
        List<EntityModel<PeliculaResponse>> peliculas = service.findAll().stream()
                .map(assembler::toModel)
                .toList();

        CollectionModel<EntityModel<PeliculaResponse>> collectionModel = CollectionModel.of(
                peliculas,
                linkTo(methodOn(PeliculaController.class).getAll()).withSelfRel()
        );

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        LocalDateTime.now(),
                        collectionModel
                )
        );
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWrapper<EntityModel<PeliculaResponse>>> getById(@PathVariable Long id) {
        PeliculaEntity pelicula = service.findById(id);
        EntityModel<PeliculaResponse> model = assembler.toModel(pelicula);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        LocalDateTime.now(),
                        model
                )
        );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<EntityModel<PeliculaResponse>>> create(@Valid @RequestBody PeliculaCreateRequest request) {
        PeliculaEntity created = service.save(request);
        EntityModel<PeliculaResponse> model = assembler.toModel(created);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(
                        new ResponseWrapper<>(
                                "OK",
                                LocalDateTime.now(),
                                model
                        )
                );
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<EntityModel<PeliculaResponse>>> update(@Valid @RequestBody PeliculaUpdateRequest request) {
        PeliculaEntity updated = service.update(request);
        EntityModel<PeliculaResponse> model = assembler.toModel(updated);

        return ResponseEntity.ok(
                new ResponseWrapper<>(
                        "OK",
                        LocalDateTime.now(),
                        model
                )
        );
    }

    @DeleteMapping("{id}")
    public ResponseEntity<ResponseWrapper<String>> delete(@PathVariable Long id) {
        service.delete(id);

        return ResponseEntity.status(HttpStatus.NO_CONTENT).body(
                new ResponseWrapper<>(
                        "OK",
                        LocalDateTime.now(),
                        "Eliminado correctamente"
                )
        );
    }
    
}
