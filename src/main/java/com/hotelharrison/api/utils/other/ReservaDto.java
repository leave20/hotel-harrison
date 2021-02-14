package com.hotelharrison.api.utils.other;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.hotelharrison.api.model.Estacionamiento;
import com.hotelharrison.api.model.Habitacion;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class ReservaDto {
    private Long id;
    private LocalDate fechaInicio;
    private LocalDate fechaFinal;
    private String placaVehiculo;
    private String codigoReserva;
    @JsonFormat(pattern = "dd-MM-yyyy HH:mm")
    private LocalDateTime creadoEn;
    private Double igv;
    private Double descuento;
    private Double precioTotal;
    private Double pagoAdelantado;
    private String estado;
    private UsuarioDto usuario;
    private HuespedDto huesped;
    private Habitacion habitacion;
    private Estacionamiento estacionamiento;
}
