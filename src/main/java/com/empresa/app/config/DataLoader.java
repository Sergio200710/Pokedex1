package com.empresa.app.config;

import com.empresa.app.model.Entrenador;
import com.empresa.app.model.Pokemon;
import com.empresa.app.model.Tipo;
import com.empresa.app.repository.EntrenadorRepository;
import com.empresa.app.repository.PokemonRepository;
import com.empresa.app.repository.TipoRepository;

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

        // Evita duplicar datos
        if (pokemonRepo.count() > 0) {
            return;
        }

        // TIPOS
        Tipo fuego = tipoRepo.save(
                new Tipo(null, "Fuego", "Tipo de fuego")
        );

        Tipo agua = tipoRepo.save(
                new Tipo(null, "Agua", "Tipo de agua")
        );

        // ENTRENADORES
        Entrenador ash = entrenadorRepo.save(
                new Entrenador(null, "Ash", "Pueblo Paleta")
        );

        Entrenador misty = entrenadorRepo.save(
                new Entrenador(null, "Misty", "Ciudad Celeste")
        );

        // POKEMONS
        Pokemon charmander = new Pokemon(null, "Charmander", 10);
        charmander.setTipo(fuego);
        charmander.setEntrenador(ash);

        Pokemon squirtle = new Pokemon(null, "Squirtle", 12);
        squirtle.setTipo(agua);
        squirtle.setEntrenador(misty);

        // GUARDAR POKEMONS
        pokemonRepo.save(charmander);
        pokemonRepo.save(squirtle);
    }
}