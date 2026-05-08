package com.empresa.app.service;

import com.empresa.app.model.Tipo;
import com.empresa.app.repository.TipoRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.util.List;

@Service
public class TipoService {

    private final TipoRepository repository;

    public TipoService(TipoRepository repository) {
        this.repository = repository;
    }

    public List<Tipo> findAll() {
        return repository.findAll();
    }

    public Tipo findById(Long id) {
        return repository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(
                        HttpStatus.NOT_FOUND, "Tipo no encontrado con id: " + id));
    }

    public Tipo save(Tipo tipo) {
        return repository.save(tipo);
    }

    public Tipo update(Long id, Tipo tipo) {
        // Comprueba que existe antes de actualizar
        findById(id);
        tipo.setId(id);
        return repository.save(tipo);
    }

    public void delete(Long id) {
        // Comprueba que existe antes de borrar
        findById(id);
        repository.deleteById(id);
    }
}