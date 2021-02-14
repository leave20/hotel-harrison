package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Producto;
import com.hotelharrison.api.service.ProductoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/productos")
public class ProductoController {

    @Autowired
    private ProductoService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Producto producto) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(producto));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Producto producto, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(producto, id));
    }
}
