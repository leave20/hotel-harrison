package com.hotelharrison.api.repository;

import com.hotelharrison.api.model.Huesped;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HuespedRepository extends JpaRepository<Huesped, Long> {
    Boolean existsHuespedByCorreoAndPassword(String c, String p);
    Huesped findByCorreo(String c);
}
