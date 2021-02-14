package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.*;
import com.hotelharrison.api.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
@Transactional
public class ContadoresService {

    @Autowired
    private HabitacionRepository repositoryHa;

    @Autowired
    private TipoHabitacionRepository repositoryTH;

    @Autowired
    private HuespedRepository repositoryHu;

    @Autowired
    private ReservaRepository repositoryR;

    @Autowired
    private FacturaRepository repositoryF;

    public Map<Object, String> countTipHabDescripcion(Long id){
        Map<Object, String> resp = new HashMap<>();

        if (!repositoryTH.existsById(id)) throw new BadRequest("No hay Habitaciones con ese tipo de Habitación");

        TipoHabitacion tipHab = repositoryTH.findById(id).orElse(null);
        Integer nro = repositoryHa.countByTipoHabitacionId(id);

        resp.put(tipHab.getNombre(), nro.toString());

        return resp;
    }

    public Integer countTipoHabitacion(Long id){
        Integer nro = repositoryHa.countByTipoHabitacionId(id);
        if (nro <= 0) throw new BadRequest("No hay Habitaciones con ese tipo de Habitación");

        return nro;
    }

    public Integer countHuespedes(){
        List<Huesped> huesped = repositoryHu.findAll();
        if (huesped.isEmpty()) throw new NotFound("No se encontraron registros de Huespedes");

        return huesped.size();
    }

    public Integer countHabitaciones(){
        List<Habitacion> habitacion = repositoryHa.findAll();
        if (habitacion.isEmpty()) throw new NotFound("No se encontraron registros de Habitaciones");

        return habitacion.size();
    }

    public Integer countReservas(){
        List<Reserva> reservas = repositoryR.findAll();
        if (reservas.isEmpty()) throw new NotFound("No se encontraron registros de Reservas");

        return reservas.size();
    }

    public Integer countVentas(){
        List<Factura> facturas = repositoryF.findAll();
        if (facturas.isEmpty()) throw new NotFound("No se encontraron registros de Ventas");

        return facturas.size();
    }

}
