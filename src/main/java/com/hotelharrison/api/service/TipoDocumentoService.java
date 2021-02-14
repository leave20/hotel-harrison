package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.TipoDocumento;
import com.hotelharrison.api.repository.TipoDocumentoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class TipoDocumentoService {

    @Autowired
    private TipoDocumentoRepository repository;

    public List<TipoDocumento> getAll() {
        List<TipoDocumento> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Tipos de Documentos");

        return lista;
    }

    public TipoDocumento findById(Long id) {
        if (!repository.existsById(id)) throw new NotFound("No se encontró Tipo de Documento con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public TipoDocumento create(TipoDocumento tipoDocumento) {
        if (tipoDocumento.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Tipo de Documento");
        if (tipoDocumento.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Tipo de Documento");
        tipoDocumento.setNombre(tipoDocumento.getNombre());

        return repository.save(tipoDocumento);
    }

    public TipoDocumento update(TipoDocumento tipoDocumento, Long id) {
        TipoDocumento updTipoDocumento = findById(id);

        if (tipoDocumento.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Tipo de Documento");
        if (tipoDocumento.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Tipo de Documento");
        updTipoDocumento.setNombre(tipoDocumento.getNombre());

        return repository.save(updTipoDocumento);
    }

}