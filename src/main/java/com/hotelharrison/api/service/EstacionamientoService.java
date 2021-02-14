package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Estacionamiento;
import com.hotelharrison.api.repository.EstacionamientoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class EstacionamientoService {

    @Autowired
    private EstacionamientoRepository repository;

    public List<Estacionamiento> getAll() {
        List<Estacionamiento> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Zonas de Estacionamiento");

        return lista;
    }

    public Estacionamiento findById(Long id) {
        if (!repository.existsById(id)) throw new NotFound("No se encontró Estacionamiento con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Estacionamiento create(Estacionamiento estacionamiento) {
        if (estacionamiento.getZona() == null) throw new BadRequest("Ingrese un Nombre para la Zona de Estacionamiento");
        if (estacionamiento.getZona().isEmpty()) throw new BadRequest("Ingrese un Nombre para la Zona de Estacionamiento");
        estacionamiento.setZona(estacionamiento.getZona());

        return repository.save(estacionamiento);
    }

    public Estacionamiento update(Estacionamiento estacionamiento, Long id) {
        Estacionamiento updEstacionamiento = findById(id);

        if (estacionamiento.getZona() == null) throw new BadRequest("Ingrese un Nombre para la Zona de Estacionamiento");
        if (estacionamiento.getZona().isEmpty()) throw new BadRequest("Ingrese un Nombre para la Zona de Estacionamiento");
        updEstacionamiento.setZona(estacionamiento.getZona());

        return repository.save(updEstacionamiento);
    }

    public Estacionamiento changeStatus(Long id) {
        Estacionamiento estado = findById(id);
        estado.setEstado(!estado.getEstado());

        return repository.save(estado);
    }
}
