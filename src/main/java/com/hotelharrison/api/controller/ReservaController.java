package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Reserva;
import com.hotelharrison.api.service.ReservaService;
import com.hotelharrison.api.utils.other.FechasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/reservas")
public class ReservaController {

    @Autowired
    private ReservaService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Reserva reserva) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(reserva));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Reserva reserva, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(reserva, id));
    }

    @PatchMapping("/{id}/{dato}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @PathVariable String dato) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id, dato));
    }

    @PostMapping("/find-date")
    public ResponseEntity<?> findDate(@RequestBody FechasDto fechas) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findDate(fechas));
    }

    @PostMapping("/find-dateTime")
    public ResponseEntity<?> findByBusyDateTime(@RequestBody FechasDto fechas) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByBusyDateTime(fechas));
    }

    @GetMapping("/huesped/{id}")
    public ResponseEntity<?> findByHuespedId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByHuesped(id));
    }

    @GetMapping("/codigo/{codigo}/{idHabitacion}")
    public ResponseEntity<?> findByCodigoReserva(@PathVariable String codigo, @PathVariable Long idHabitacion) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByCodigoReserva(codigo, idHabitacion));
    }

    @GetMapping("/habitacion/{id}")
    public ResponseEntity<?> findByHabitacionId(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findByHabitacion(id));
    }
}
