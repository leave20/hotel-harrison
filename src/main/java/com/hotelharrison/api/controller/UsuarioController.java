package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Usuario;
import com.hotelharrison.api.service.UsuarioService;
import com.hotelharrison.api.utils.other.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @GetMapping("/find/{dni}")
    public ResponseEntity<?> findByDni(@PathVariable String dni) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByDni(dni));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Usuario usuario) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(usuario));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Usuario usuario, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(usuario, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id));
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        return service.login(login);
    }
}

