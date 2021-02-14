package com.hotelharrison.api.repository;

import com.hotelharrison.api.model.Consumo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface ConsumoRepository extends JpaRepository<Consumo, Long> {
    List<Consumo> findByReservaId(Long id);
}
