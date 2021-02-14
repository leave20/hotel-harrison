package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.TipoHabitacion;
import com.hotelharrison.api.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipoHabitacionService {

    @Autowired
    private TipoHabitacionRepository repository;

    public List<TipoHabitacion> getAll(){
        List<TipoHabitacion> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Tipos de Habitaciones");

        return lista;
    }

    public TipoHabitacion findById(Long id){
        if (!repository.existsById(id)) throw new NotFound("No se encontró Tipo de Habitación con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public TipoHabitacion create(TipoHabitacion tipoHabitacion) {
        if (tipoHabitacion.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Tipo de Habitación");
        if (tipoHabitacion.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Tipo de Habitación");
        tipoHabitacion.setNombre(tipoHabitacion.getNombre());

        if (tipoHabitacion.getNroCamas() == null) throw new BadRequest("Ingrese el Número de Camas");
        if (tipoHabitacion.getNroCamas() < 0) throw new BadRequest("Ingrese el Número de Camas mayor a 0");
        tipoHabitacion.setNroCamas(tipoHabitacion.getNroCamas());

        if (tipoHabitacion.getPrecio() == null) throw new BadRequest("Ingrese el Precio para el Tipo de Habitación");
        if (tipoHabitacion.getPrecio() < 0) throw new BadRequest("Ingrese el Precio mayor a 0");
        tipoHabitacion.setPrecio(tipoHabitacion.getPrecio());

        return repository.save(tipoHabitacion);
    }

    public TipoHabitacion update(TipoHabitacion tipoHabitacion, Long id) {
        TipoHabitacion updTipoHabitacion = findById(id);

        if (tipoHabitacion.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Tipo de Habitación");
        if (tipoHabitacion.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Tipo de Habitación");
        updTipoHabitacion.setNombre(tipoHabitacion.getNombre());

        if (tipoHabitacion.getNroCamas() == null) throw new BadRequest("Ingrese el Número de Camas");
        if (tipoHabitacion.getNroCamas() < 0) throw new BadRequest("Ingrese el N+umero de Camas mayor a 0");
        updTipoHabitacion.setNroCamas(tipoHabitacion.getNroCamas());

        if (tipoHabitacion.getPrecio() == null) throw new BadRequest("Ingrese el Precio para el Tipo de Habitación");
        if (tipoHabitacion.getPrecio() < 0) throw new BadRequest("Ingrese el Precio mayor a 0");
        updTipoHabitacion.setPrecio(tipoHabitacion.getPrecio());

        return repository.save(updTipoHabitacion);
    }
}
