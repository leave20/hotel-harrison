package com.hotelharrison.api.controller;

import com.hotelharrison.api.service.ContadoresService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/contadores")
public class ContadoresController {

    @Autowired
    private ContadoresService serviceH;

    @GetMapping("/tipo-habitacion/{id}")
    public ResponseEntity<?> countTipoHabitacion(@PathVariable Long id){
        return ResponseEntity.status(HttpStatus.OK).body(serviceH.countTipoHabitacion(id));
    }

    @GetMapping("/huespedes")
    public ResponseEntity<?> countHuesped(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceH.countHuespedes());
    }

    @GetMapping("/habitaciones")
    public ResponseEntity<?> countHabitacion(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceH.countHabitaciones());
    }

    @GetMapping("/reservas")
    public ResponseEntity<?> countReservas(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceH.countReservas());
    }

    @GetMapping("/ventas")
    public ResponseEntity<?> countVentas(){
        return ResponseEntity.status(HttpStatus.OK).body(serviceH.countVentas());
    }
}
