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
public class Huesped {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    //@Column(nullable = false)
    private String nombre;

    //@Column(nullable = false)
    private String apellido;

    //@Column(nullable = false)
    private String documento;

    //@Column(nullable = false, unique = true)
    private String correo;

    //@Column(nullable = false)
    private String password;

    //@Column(nullable = false)
    private String telefono;

    //@Column(nullable = false)
    private Boolean estado;

    //@JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private TipoDocumento tipoDocumento;

    @PrePersist
    void Prepersit() {
        estado = true;
    }
}
