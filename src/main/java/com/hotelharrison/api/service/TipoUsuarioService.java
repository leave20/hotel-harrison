package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.TipoUsuario;
import com.hotelharrison.api.repository.TipoUsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipoUsuarioService {

    @Autowired
    private TipoUsuarioRepository repository;

    public List<TipoUsuario> getAll() {
        List<TipoUsuario> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Tipos de Usuarios");

        return lista;
    }

    public TipoUsuario findById(Long id) {
        if (!repository.existsById(id)) throw new NotFound("No se encontró Tipo de Usuario con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public TipoUsuario create(TipoUsuario tipoUsuario) {
        if (tipoUsuario.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Tipo de Usuario");
        if (tipoUsuario.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Tipo de Usuario");
        tipoUsuario.setNombre(tipoUsuario.getNombre());

        return repository.save(tipoUsuario);
    }

    public TipoUsuario update(TipoUsuario tipoUsuario, Long id) {
        TipoUsuario updTipoUsuario = findById(id);

        if (tipoUsuario.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Tipo de Usuario");
        if (tipoUsuario.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Tipo de Usuario");
        updTipoUsuario.setNombre(tipoUsuario.getNombre());

        return repository.save(updTipoUsuario);
    }
}