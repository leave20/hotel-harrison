package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Consumo;
import com.hotelharrison.api.service.ConsumoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/consumos")
public class ConsumoController {

    @Autowired
    private ConsumoService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Consumo consumo) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(consumo));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Consumo consumo, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(consumo, id));
    }

    @GetMapping("/reservas/{id}")
    public ResponseEntity<?> findByReservaId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findbyReserva(id));
    }
}
