package com.empresa.app.service;

import com.empresa.app.model.Entrenador;
import com.empresa.app.repository.EntrenadorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class EntrenadorService {

    private final EntrenadorRepository repository;

    public EntrenadorService(EntrenadorRepository repository) {
        this.repository = repository;
    }

    public List<Entrenador> findAll() {
        return repository.findAll();
    }

    public Entrenador findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Entrenador no encontrado con id: " + id));
    }

    public Entrenador save(Entrenador entrenador) {
        return repository.save(entrenador);
    }

    public Entrenador update(Long id, Entrenador entrenador) {
        // Comprueba que existe antes de actualizar
        findById(id);
        entrenador.setId(id);
        return repository.save(entrenador);
    }

    public void delete(Long id) {
        // Comprueba que existe antes de borrar
        findById(id);
        repository.deleteById(id);
    }
}