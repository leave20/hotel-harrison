package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.TipoHabitacion;
import com.hotelharrison.api.service.TipoHabitacionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipos-habitacion")
public class TipoHabitacionController {

    @Autowired
    private TipoHabitacionService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoHabitacion tipoHabitacion) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(tipoHabitacion));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TipoHabitacion tipoHabitacion, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(tipoHabitacion, id));
    }
}
