package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.TipoDocumento;
import com.hotelharrison.api.service.TipoDocumentoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipos-documento")
public class TipoDocumentoController {

    @Autowired
    private TipoDocumentoService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoDocumento tipoDocumento) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(tipoDocumento));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TipoDocumento tipoDocumento, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(tipoDocumento, id));
    }
}
