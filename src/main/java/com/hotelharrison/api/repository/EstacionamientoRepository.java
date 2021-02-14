package com.hotelharrison.api.repository;

import com.hotelharrison.api.model.Estacionamiento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EstacionamientoRepository extends JpaRepository<Estacionamiento, Long> {
}
