package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Nivel;
import com.hotelharrison.api.service.NivelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/niveles")
public class NivelController {

    @Autowired
    private NivelService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Nivel nivel) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(nivel));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Nivel nivel, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(nivel, id));
    }
}
