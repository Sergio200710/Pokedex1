package com.empresa.app.controller;

import com.empresa.app.model.Tipo;
import com.empresa.app.service.TipoService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/tipos")
public class TipoController {

    private final TipoService service;

    public TipoController(TipoService service) {
        this.service = service;
    }

    // GET /tipos → 200 OK
    @GetMapping
    public ResponseEntity<List<Tipo>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET /tipos/{id} → 200 OK o 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Tipo> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // POST /tipos → 201 CREATED
    @PostMapping
    public ResponseEntity<Tipo> create(@Valid @RequestBody Tipo tipo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(tipo));
    }

    // PUT /tipos/{id} → 200 OK o 404 NOT FOUND
    @PutMapping("/{id}")
    public ResponseEntity<Tipo> update(@PathVariable Long id, @Valid @RequestBody Tipo tipo) {
        return ResponseEntity.ok(service.update(id, tipo));
    }

    // DELETE /tipos/{id} → 204 NO CONTENT o 404 NOT FOUND
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}