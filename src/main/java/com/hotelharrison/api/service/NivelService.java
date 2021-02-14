package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Nivel;
import com.hotelharrison.api.repository.NivelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class NivelService {

    @Autowired
    private NivelRepository repository;

    public List<Nivel> getAll(){
        List<Nivel> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Niveles");

        return lista;
    }

    public Nivel findById(Long id){
        if (!repository.existsById(id)) throw new NotFound("No se encontró Nivel con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Nivel create(Nivel nivel){
        if (nivel.getNombre() == null) throw new BadRequest("Ingrese un Nombre de Nivel");
        if (nivel.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre de Nivel");
        nivel.setNombre(nivel.getNombre());

        return repository.save(nivel);
    }

    public Nivel update(Nivel nivel, Long id) {
        Nivel updNivel = findById(id);

        if (nivel.getNombre() == null) throw new BadRequest("Ingrese un Nombre de Nivel");
        if (nivel.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre de Nivel");
        updNivel.setNombre(nivel.getNombre());

        return repository.save(updNivel);
    }
}
