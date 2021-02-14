package com.hotelharrison.api.repository;

import com.hotelharrison.api.model.Reserva;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Repository
public interface ReservaRepository extends JpaRepository<Reserva, Long> {
    List<Reserva> findByCreadoEnBetween(LocalDateTime star, LocalDateTime end);
    List<Reserva> findByHuespedId(Long id);
    List<Reserva> findByFechaInicioBetween(LocalDate a, LocalDate z);
    List<Reserva> findByHabitacionId(Long id);
    Boolean existsByCodigoReserva(String c);
    Reserva findByCodigoReserva(String c);
}
