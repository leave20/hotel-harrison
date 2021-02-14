package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Huesped;
import com.hotelharrison.api.service.HuespedService;
import com.hotelharrison.api.utils.other.LoginDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/huespedes")
public class HuespedController {

    @Autowired
    private HuespedService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Huesped huesped) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(huesped));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Huesped huesped, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(huesped, id));
    }

    @PatchMapping("/{id}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id));
    }

    /*@PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        return ResponseEntity.status(HttpStatus.OK).body(service.login(login));
    }*/

    @PostMapping("/login")
    public ResponseEntity<?> login(@RequestBody LoginDto login) {
        return service.login(login);
    }
}
