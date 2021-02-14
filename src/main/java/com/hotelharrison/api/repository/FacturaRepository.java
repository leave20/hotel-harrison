package com.hotelharrison.api.repository;

import com.hotelharrison.api.model.Factura;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface FacturaRepository extends JpaRepository<Factura, Long> {
    List<Factura> findByCreadoEnBetween(LocalDateTime star, LocalDateTime end);
    Factura findByReservaId(Long idReserva);
}
