package com.empresa.app.config;

import com.empresa.app.model.*;
import com.empresa.app.repository.*;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataLoader implements CommandLineRunner {

    private final TipoRepository tipoRepo;
    private final EntrenadorRepository entrenadorRepo;
    private final PokemonRepository pokemonRepo;

    public DataLoader(TipoRepository tipoRepo,
                      EntrenadorRepository entrenadorRepo,
                      PokemonRepository pokemonRepo) {
        this.tipoRepo = tipoRepo;
        this.entrenadorRepo = entrenadorRepo;
        this.pokemonRepo = pokemonRepo;
    }

    @Override
    public void run(String... args) {

        // Evita insertar datos duplicados en cada arranque
        if (pokemonRepo.count() > 0) return;

        // Crear tipos
        Tipo fuego = tipoRepo.save(new Tipo(null, "Fuego", "Tipo de fuego"));
        Tipo agua  = tipoRepo.save(new Tipo(null, "Agua",  "Tipo de agua"));

        // Crear entrenadores
        Entrenador ash   = entrenadorRepo.save(new Entrenador(null, "Ash",   "Pueblo Paleta"));
        Entrenador misty = entrenadorRepo.save(new Entrenador(null, "Misty", "Ciudad Celeste"));

        // Crear Pokémon con sus relaciones correctamente asignadas
        Pokemon charmander = new Pokemon(null, "Charmander", 10);
        charmander.setTipo(fuego);
        charmander.setEntrenador(ash);
        pokemonRepo.save(charmander);

        Pokemon squirtle = new Pokemon(null, "Squirtle", 12);
        squirtle.setTipo(agua);
        squirtle.setEntrenador(misty);
        pokemonRepo.save(squirtle);
    }
}