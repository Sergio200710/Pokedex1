package com.empresa.app.service;

import com.empresa.app.model.Pokemon;
import com.empresa.app.repository.PokemonRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class PokemonService {

    private final PokemonRepository repository;

    public PokemonService(PokemonRepository repository) {
        this.repository = repository;
    }

    public List<Pokemon> findAll() {
        return repository.findAll();
    }

    public Pokemon findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Pokémon no encontrado con id: " + id));
    }

    public Pokemon save(Pokemon pokemon) {
        return repository.save(pokemon);
    }

    public Pokemon update(Long id, Pokemon pokemon) {
        // Comprueba que existe antes de actualizar
        findById(id);
        pokemon.setId(id);
        return repository.save(pokemon);
    }

    public void delete(Long id) {
        // Comprueba que existe antes de borrar
        findById(id);
        repository.deleteById(id);
    }
}