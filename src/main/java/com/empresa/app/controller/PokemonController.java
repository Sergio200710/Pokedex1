package com.empresa.app.controller;

import com.empresa.app.model.Pokemon;
import com.empresa.app.service.PokemonService;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/pokemons")
public class PokemonController {

    private final PokemonService service;

    public PokemonController(PokemonService service) {
        this.service = service;
    }

    // GET /pokemons → 200 OK
    @GetMapping
    public ResponseEntity<List<Pokemon>> getAll() {
        return ResponseEntity.ok(service.findAll());
    }

    // GET /pokemons/{id} → 200 OK o 404 NOT FOUND
    @GetMapping("/{id}")
    public ResponseEntity<Pokemon> getById(@PathVariable Long id) {
        return ResponseEntity.ok(service.findById(id));
    }

    // POST /pokemons → 201 CREATED
    @PostMapping
    public ResponseEntity<Pokemon> create(@Valid @RequestBody Pokemon pokemon) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.save(pokemon));
    }

    // PUT /pokemons/{id} → 200 OK o 404 NOT FOUND
    @PutMapping("/{id}")
    public ResponseEntity<Pokemon> update(@PathVariable Long id, @Valid @RequestBody Pokemon pokemon) {
        return ResponseEntity.ok(service.update(id, pokemon));
    }

    // DELETE /pokemons/{id} → 204 NO CONTENT o 404 NOT FOUND
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        service.delete(id);
        return ResponseEntity.noContent().build();
    }
}