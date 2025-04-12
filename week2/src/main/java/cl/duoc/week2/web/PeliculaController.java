package cl.duoc.week2.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.week2.service.IPeliculaService;
import cl.duoc.week2.web.dtos.PeliculaCreateRequest;
import cl.duoc.week2.web.dtos.PeliculaResponse;
import cl.duoc.week2.web.dtos.PeliculaUpdateRequest;
import cl.duoc.week2.web.dtos.ResponseWrapper;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;



@RestController
@RequestMapping(path = "peliculas")
@RequiredArgsConstructor
public class PeliculaController {
    private final IPeliculaService service;

    @GetMapping
    public ResponseEntity<ResponseWrapper<List<PeliculaResponse>>> getAll() {
        return ResponseEntity.ok(
            new ResponseWrapper<>("OK", LocalDateTime.now(), service.findAll().stream()
                .map(PeliculaResponse::fromDomain)
                .toList()));
    }

    @GetMapping("{id}")
    public ResponseEntity<ResponseWrapper<PeliculaResponse>> getById(@PathVariable Long id) {
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "OK",
                LocalDateTime.now(),
                PeliculaResponse.fromDomain(service.findById(id))
                )
            );
    }

    @PostMapping
    public ResponseEntity<ResponseWrapper<PeliculaResponse>> create(@Valid @RequestBody PeliculaCreateRequest request) {
        return ResponseEntity.status(HttpStatus.CREATED).body(
            new ResponseWrapper<>(
                "OK",
                LocalDateTime.now(),
                PeliculaResponse.fromDomain(service.save(request))
            )
        );
    }

    @PutMapping
    public ResponseEntity<ResponseWrapper<PeliculaResponse>>update(@Valid @RequestBody PeliculaUpdateRequest entity) {
        return ResponseEntity.ok(
            new ResponseWrapper<>(
                "OK",
                LocalDateTime.now(),
                PeliculaResponse.fromDomain(service.update(entity))
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
                "Eliinado correctamente"
            )
        );
    }
    
}
