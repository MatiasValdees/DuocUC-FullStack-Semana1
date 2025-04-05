package cl.duoc.week2.web;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.week2.service.IPeliculaService;
import cl.duoc.week2.web.dtos.PeliculaResponse;
import cl.duoc.week2.web.dtos.ResponseWrapper;
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
}
