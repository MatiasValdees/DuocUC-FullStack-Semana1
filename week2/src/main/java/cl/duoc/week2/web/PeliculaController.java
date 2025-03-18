package cl.duoc.week2.web;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import cl.duoc.week2.service.IPeliculaService;
import cl.duoc.week2.web.dtos.PeliculaResponse;
import lombok.RequiredArgsConstructor;

@RestController
@RequestMapping(path = "peliculas")
@RequiredArgsConstructor
public class PeliculaController {
    private final IPeliculaService service;

    @GetMapping
    public ResponseEntity<List<PeliculaResponse>> getAll() {
        return ResponseEntity.ok(service.findAll()
                .stream()
                .map(PeliculaResponse::fromDomain)
                .toList());
    }

    @GetMapping("{id}")
    public ResponseEntity<PeliculaResponse> getById(@PathVariable Long id) {
        return ResponseEntity.ok(PeliculaResponse.fromDomain(service.findById(id)));
    }
}
