package com.hotelharrison.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Reserva {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private LocalDate fechaInicio;

    @Column(nullable = false)
    private LocalDate fechaFinal;

    private String placaVehiculo;

    @Column(unique = true)
    private String codigoReserva;

    @Column(nullable = false)
    private LocalDateTime creadoEn;

    @Column(nullable = false)
    private Double igv;

    private Double descuento;

    @Column(nullable = false)
    private Double precioTotal;

    private Double pagoAdelantado;

    @Column(nullable = false)
    private String estado;

    @ManyToOne(fetch = FetchType.LAZY)
    private Usuario usuario;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Huesped huesped;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Habitacion habitacion;

    @ManyToOne(fetch = FetchType.LAZY)
    private Estacionamiento estacionamiento;

    @PrePersist
    void Prepersit() {
        creadoEn = LocalDateTime.now();
        estado = "PENDIENTE";

        if (placaVehiculo.isEmpty()) {
            placaVehiculo = "NO TIENE";
        }
    }
}
