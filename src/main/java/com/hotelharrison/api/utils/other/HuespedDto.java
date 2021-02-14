package com.hotelharrison.api.utils.other;

import com.hotelharrison.api.model.TipoDocumento;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class HuespedDto {
    private Long id;
    private String nombre;
    private String apellido;
    private String documento;
    private String correo;
    private String telefono;
    private Boolean estado;
    private TipoDocumento tipoDocumento;
}
