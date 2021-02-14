package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.TipoUsuario;
import com.hotelharrison.api.service.TipoUsuarioService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/tipos-usuario")
public class TipoUsuarioController {

    @Autowired
    private TipoUsuarioService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody TipoUsuario tipoUsuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(tipoUsuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody TipoUsuario tipoUsuario, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(tipoUsuario, id));
    }
}
