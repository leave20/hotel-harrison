package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Usuario;
import com.hotelharrison.api.repository.TipoUsuarioRepository;
import com.hotelharrison.api.repository.UsuarioRepository;
import com.hotelharrison.api.utils.MapperUtil;
import com.hotelharrison.api.utils.other.LoginDto;
import com.hotelharrison.api.utils.other.UsuarioDto;
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
public class UsuarioService {

    @Autowired
    private UsuarioRepository repository;

    @Autowired
    private TipoUsuarioRepository repositoryTU;

    public List<?> getAll() {
        List<Usuario> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Usuarios");

        return MapperUtil.Usuarios(lista);
    }

    public Usuario findById(Long id) {
        if (!repository.existsById(id)) throw new NotFound("No se encontró Usuario con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public UsuarioDto findByDni(String d) {
        if (repository.findByDni(d) == null) throw new NotFound("No se encontró Usuario con DNI: " + d);

        return MapperUtil.UsuarioUnique(repository.findByDni(d));
    }

    public Usuario create(Usuario usuario) {
        if (usuario.getNombre() == null) throw new BadRequest("Ingrese un Nombre");
        if (usuario.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre");
        usuario.setNombre(usuario.getNombre());

        if (usuario.getApellido() == null) throw new BadRequest("Ingrese un Apellido");
        if (usuario.getApellido().isEmpty()) throw new BadRequest("Ingrese un Apellido");
        usuario.setApellido(usuario.getApellido());

        if (usuario.getDni() == null) throw new BadRequest("Ingrese un DNI");
        else {
            if (usuario.getDni().length() < 8) throw new BadRequest("Ingrese correctamente el DNI(8 digitos)");
            if (usuario.getDni().length() > 8) throw new BadRequest("Ingrese correctamente el DNI(8 digitos)");
            Usuario dni = repository.findByDni(usuario.getDni());
            if (dni != null) throw new BadRequest("El DNI ya existe");
            usuario.setDni(usuario.getDni());
        }

        if (usuario.getCorreo() == null) throw new BadRequest("Ingrese un Correo");
        if (usuario.getCorreo().isEmpty()) throw new BadRequest("Ingrese un Correo");
        Usuario correo = repository.findByCorreo(usuario.getCorreo());
        if (correo != null) throw new BadRequest("El Correo ya existe");
        else usuario.setCorreo(usuario.getCorreo());

        if (usuario.getPassword() == null) throw new BadRequest("Ingrese una Contraseña");
        if (usuario.getPassword().isEmpty()) throw new BadRequest("Ingrese una Contraseña");
        usuario.setPassword(usuario.getPassword());

        if (usuario.getDireccion() == null) throw new BadRequest("Ingrese una Dirección");
        if (usuario.getDireccion().isEmpty()) throw new BadRequest("Ingrese una Dirección");
        usuario.setDireccion(usuario.getDireccion());

        if (usuario.getTelefono() == null) throw new BadRequest("Ingrese un Telefono o Celular");
        else {
            if (usuario.getTelefono().length() < 7)
                throw new BadRequest("Ingrese Correctamente el Telefono o Celular");
            if (usuario.getTelefono().length() == 8)
                throw new BadRequest("Ingrese Correctamente el Telefono o Celular(7 ó 9 digitos)");
            if (usuario.getTelefono().length() > 9)
                throw new BadRequest("Ingrese Correctamente el Telefono o Celular");
            usuario.setTelefono(usuario.getTelefono());
        }

        Long tipoUsuarioId = usuario.getTipoUsuario().getId();
        if (tipoUsuarioId == null) throw new BadRequest("Ingrese un Tipo de Usuario");
        if (usuario.getTipoUsuario() != null) {
            boolean tipoUsuario = repositoryTU.existsById(tipoUsuarioId);
            if (!tipoUsuario) throw new BadRequest("No se encontró el Tipo de Usuario con id: " + tipoUsuarioId);
            usuario.setTipoUsuario(usuario.getTipoUsuario());
        }

        return repository.save(usuario);
    }

    public Usuario update(Usuario usuario, Long id) {
        Usuario updUsuario = findById(id);

        if (usuario.getNombre() == null) throw new BadRequest("Ingrese un Nombre");
        if (usuario.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre");
        updUsuario.setNombre(usuario.getNombre());

        if (usuario.getApellido() == null) throw new BadRequest("Ingrese un Apellido");
        if (usuario.getApellido().isEmpty()) throw new BadRequest("Ingrese un Apellido");
        updUsuario.setApellido(usuario.getApellido());

        if (usuario.getDni() == null) throw new BadRequest("Ingrese un DNI");
        else {
            if (usuario.getDni().length() < 8) throw new BadRequest("Ingrese correctamente el DNI(8 digitos)");
            if (usuario.getDni().length() > 8) throw new BadRequest("Ingrese correctamente el DNI(8 digitos)");
            if (usuario.getDni().equals(updUsuario.getDni())) updUsuario.setDni(updUsuario.getDni());
            else {
                Usuario dni = repository.findByDni(usuario.getDni());
                if (dni != null) throw new BadRequest("El DNI ya existe");
                else updUsuario.setDni(usuario.getDni());
            }
        }

        if (usuario.getCorreo().isEmpty()) throw new BadRequest("Ingrese un Correo");
        if (usuario.getCorreo() == null) throw new BadRequest("Ingrese un Correo");
        if (usuario.getCorreo().equals(updUsuario.getCorreo())) updUsuario.setCorreo(updUsuario.getCorreo());
        else {
            Usuario correo = repository.findByCorreo(usuario.getCorreo());
            if (correo != null) throw new BadRequest("El Correo ya existe");
            else updUsuario.setCorreo(usuario.getCorreo());
        }

        if (usuario.getPassword() == null) throw new BadRequest("Ingrese una Contraseña");
        if (usuario.getPassword().isEmpty()) throw new BadRequest("Ingrese una Contraseña");
        updUsuario.setPassword(usuario.getPassword());

        if (usuario.getDireccion() == null) throw new BadRequest("Ingrese una Dirección");
        if (usuario.getDireccion().isEmpty()) throw new BadRequest("Ingrese una Dirección");
        updUsuario.setDireccion(usuario.getDireccion());

        if (usuario.getTelefono() == null) throw new BadRequest("Ingrese un Telefono o Celular");
        else {
            if (usuario.getTelefono().length() < 7)
                throw new BadRequest("Ingrese Correctamente el Telefono o Celular");
            if (usuario.getTelefono().length() == 8)
                throw new BadRequest("Ingrese Correctamente el Telefono o Celular(7 ó 9 digitos)");
            if (usuario.getTelefono().length() > 9)
                throw new BadRequest("Ingrese Correctamente el Telefono o Celular");
            updUsuario.setTelefono(usuario.getTelefono());
        }

        Long tipoUsuarioId = usuario.getTipoUsuario().getId();
        if (tipoUsuarioId == null) throw new BadRequest("Ingrese un Tipo de Usuario");
        if (usuario.getTipoUsuario() != null) {
            boolean tipoUsuario = repositoryTU.existsById(tipoUsuarioId);
            if (!tipoUsuario)
                throw new BadRequest("No se encontró el Tipo de Usuario con id: " + tipoUsuarioId);
            updUsuario.setTipoUsuario(usuario.getTipoUsuario());
        }

        return repository.save(updUsuario);
    }

    public Usuario changeStatus(Long id) {
        Usuario usuario = findById(id);
        usuario.setEstado(!usuario.getEstado());

        return repository.save(usuario);
    }

    public ResponseEntity<?> login(LoginDto usuario) {
        String c = usuario.getCorreo();
        String p = usuario.getPassword();
        Map<String, Object> resp = new HashMap<>();

        if (c.isEmpty()) throw new BadRequest("Ingrese el Correo");
        if (p.isEmpty()) throw new BadRequest("Ingrese la Contraseña");

        if (repository.existsUsuarioByCorreoAndPassword(c, p)) {
            UsuarioDto userLog = MapperUtil.UsuarioUnique(repository.findByCorreo(c));
            resp.put("Valido", true);
            resp.put("Mensaje", "Credenciales Válidas");
            resp.put("Usuario", userLog);

            return new ResponseEntity<>(resp, HttpStatus.OK);

        } else {
            resp.put("Valido", false);
            resp.put("Mensaje", "Credenciales NO Válidas");

            return new ResponseEntity<>(resp, HttpStatus.UNAUTHORIZED);
        }
    }
}
