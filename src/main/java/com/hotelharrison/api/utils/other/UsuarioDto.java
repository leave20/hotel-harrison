package com.hotelharrison.api.utils.other;

import com.hotelharrison.api.model.TipoUsuario;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UsuarioDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String dni;
    private String correo;
    private String direccion;
    private String telefono;
    private Boolean estado;
    private TipoUsuario tipoUsuario;
}
