package com.hotelharrison.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Habitacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String nombre;

    private String descripcion;

    private String imagen;

    @Column(nullable = false)
    private String estado;

    private Double descuento;

    private Boolean promocion;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Nivel nivel;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoHabitacion tipoHabitacion;

    @PrePersist
    void Prepersit() {
        estado = "DISPONIBLE";
    }
}
