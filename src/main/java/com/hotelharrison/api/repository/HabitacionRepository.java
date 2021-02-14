package com.hotelharrison.api.repository;

import com.hotelharrison.api.model.Habitacion;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface HabitacionRepository extends JpaRepository<Habitacion, Long> {
    List<Habitacion> findByEstado(String estado);
    List<Habitacion> findByIdNotIn(List<?> datos);
    List<Habitacion> findByTipoHabitacionNroCamas(Integer nro);
    List<Habitacion> findByPromocion(Boolean prom);

    Integer countByTipoHabitacionId(Long id);
}
