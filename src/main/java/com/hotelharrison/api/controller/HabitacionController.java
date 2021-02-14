package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Habitacion;
import com.hotelharrison.api.service.HabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/habitaciones")
public class HabitacionController {

    @Autowired
    private HabitacionService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Habitacion habitacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(habitacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Habitacion habitacion, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(habitacion, id));
    }

    @PatchMapping("/{id}/{dato}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @PathVariable String dato) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id, dato));
    }

    /*
    @GetMapping("/disponibles")
    public ResponseEntity<?> findAllByEstado() {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByDisponible());
    }

    @GetMapping("/By/{status}")
    public ResponseEntity<?> findByEstado(@PathVariable String status) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByEstado(status));
    }
    */

    @GetMapping("/camas/{nro}")
    public ResponseEntity<?> findByNroCamas(@PathVariable Integer nro) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByNroCamas(nro));
    }

    @GetMapping("/promociones")
    public ResponseEntity<?> findByPromociones(){
        return ResponseEntity.status(HttpStatus.OK).body(service.findByPromociones());
    }
}
