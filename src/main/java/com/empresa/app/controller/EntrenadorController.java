package com.empresa.app.controller;

import com.empresa.app.model.Entrenador;
import com.empresa.app.service.EntrenadorService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entrenadores")
public class EntrenadorController {

    private final EntrenadorService service;

    public EntrenadorController(EntrenadorService service) {
        this.service = service;
    }

    // GET /entrenadores → 200 OK
    @GetMapping
    public ResponseEntity<List<Entrenador>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET /entrenadores/{id} → 200 OK o 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Entrenador> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // POST /entrenadores → 201 CREATED
    @PostMapping
    public ResponseEntity<Entrenador> create(@Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(entrenador));
    }

    // PUT /entrenadores/{id} → 200 OK o 404 NOT FOUND
    @PutMapping("/{id}")
    public ResponseEntity<Entrenador> update(@PathVariable Long id, @Valid @RequestBody Entrenador entrenador) {
        return ResponseEntity.ok(service.update(id, entrenador));
    }

    // DELETE /entrenadores/{id} → 204 NO CONTENT o 404 NOT FOUND
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}