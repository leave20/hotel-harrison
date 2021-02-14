package com.hotelharrison.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@Entity
public class Factura {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String ruc;

    @Column(nullable = false)
    private String razonSocial;

    @Column(nullable = false)
    private LocalDateTime creadoEn;

    private Double lateCheckOut;

    @Column(nullable = false)
    private String estado;

    @JoinColumn(nullable = false)
    @ManyToOne(fetch = FetchType.LAZY)
    private Reserva reserva;

    @PrePersist
    void Prepersist() {
        creadoEn = LocalDateTime.now();
        estado = "PENDIENTE";

        if (lateCheckOut == null) {
            lateCheckOut = 0.0;
        }
    }
}
