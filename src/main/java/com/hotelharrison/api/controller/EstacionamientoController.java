package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Estacionamiento;
import com.hotelharrison.api.service.EstacionamientoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/estacionamientos")
public class EstacionamientoController {

    @Autowired
    private EstacionamientoService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Estacionamiento estacionamiento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(estacionamiento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Estacionamiento estacionamiento, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(estacionamiento, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id));
    }
}
