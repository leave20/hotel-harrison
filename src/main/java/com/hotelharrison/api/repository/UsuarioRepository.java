package com.hotelharrison.api.repository;

import com.hotelharrison.api.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario, Long> {
    Boolean existsUsuarioByCorreoAndPassword(String c, String p);
    Usuario findByCorreo(String c);
    Usuario findByDni(String d);
}
