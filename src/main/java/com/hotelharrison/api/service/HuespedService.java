package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Huesped;
import com.hotelharrison.api.model.TipoDocumento;
import com.hotelharrison.api.repository.HuespedRepository;
import com.hotelharrison.api.repository.TipoDocumentoRepository;
import com.hotelharrison.api.utils.MapperUtil;
import com.hotelharrison.api.utils.other.HuespedDto;
import com.hotelharrison.api.utils.other.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class HuespedService {

    @Autowired
    private HuespedRepository repository;

    @Autowired
    private TipoDocumentoRepository repositoryTd;

    public List<?> getAll() {
        List<Huesped> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Huespedes");

        return MapperUtil.Huespedes(lista);
    }

    public Huesped findById(Long id) {
        if (!repository.existsById(id)) throw new NotFound("No se encontró Huesped con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Huesped create(Huesped huesped) {
        if (huesped.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Huesped");
        if (huesped.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Huesped");
        huesped.setNombre(huesped.getNombre());

        if (huesped.getApellido() == null) throw new BadRequest("Ingrese un Apellido para el Huesped");
        if (huesped.getApellido().isEmpty()) throw new BadRequest("Ingrese un Apellido para el Huesped");
        huesped.setApellido(huesped.getApellido());

        if (huesped.getCorreo() == null) huesped.setCorreo(null);
        else {
            if (huesped.getCorreo().isEmpty()) huesped.setCorreo(null);
            else {
                Huesped correo = repository.findByCorreo(huesped.getCorreo());
                if (correo != null) throw new BadRequest("El Correo ya existe");
                huesped.setCorreo(huesped.getCorreo());
            }
        }

        if (huesped.getTipoDocumento() == null) {
            TipoDocumento tipoDocumento = repositoryTd.findById(1L).orElse(null);
            huesped.setTipoDocumento(tipoDocumento);
        }

        return repository.save(huesped);
    }

    public Huesped update(Huesped huesped, Long id) {
        Huesped updHuesped = findById(id);
        updHuesped.setNombre(huesped.getNombre());
        updHuesped.setApellido(huesped.getApellido());
        updHuesped.setDocumento(huesped.getDocumento());
        updHuesped.setCorreo(huesped.getCorreo());
        updHuesped.setPassword(huesped.getPassword());
        updHuesped.setTelefono(huesped.getTelefono());
        updHuesped.setTipoDocumento(huesped.getTipoDocumento());

        return repository.save(updHuesped);
    }

    public Huesped changeStatus(Long id) {
        Huesped huesped = findById(id);
        huesped.setEstado(!huesped.getEstado());

        return repository.save(huesped);
    }

    public ResponseEntity<?> login(LoginDto huesped) {
        String c = huesped.getCorreo();
        String p = huesped.getPassword();
        Map<String, Object> resp = new HashMap<>();

        if (c.isEmpty()) throw new BadRequest("Ingrese el Correo");
        if (p.isEmpty()) throw new BadRequest("Ingrese la Contraseña");

        if (repository.existsHuespedByCorreoAndPassword(c, p)) {
            HuespedDto hostLog = MapperUtil.HuespedUnique(repository.findByCorreo(c));
            resp.put("Valido", true);
            resp.put("Mensaje", "Credenciales Válidas");
            resp.put("Huesped", hostLog);

            return new ResponseEntity<>(resp, HttpStatus.OK);

        } else {
            resp.put("Valido", false);
            resp.put("Mensaje", "Credenciales NO Válidas");

            return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
        }
    }
}