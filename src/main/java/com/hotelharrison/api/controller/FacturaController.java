package com.hotelharrison.api.controller;

import com.hotelharrison.api.model.Factura;
import com.hotelharrison.api.service.FacturaService;
import com.hotelharrison.api.utils.other.FechasDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facturas")
public class FacturaController {

    @Autowired
    private FacturaService service;

    @GetMapping
    public ResponseEntity<?> findAll() {
        return ResponseEntity.status(HttpStatus.OK).body(service.getAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> findById(@PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findById(id));
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody Factura factura) {
        return ResponseEntity.status(HttpStatus.CREATED).body(service.create(factura));
    }

    @PutMapping("/{id}")
    public ResponseEntity<?> update(@RequestBody Factura factura, @PathVariable Long id) {
        return ResponseEntity.status(HttpStatus.OK).body(service.update(factura, id));
    }

    @PatchMapping("/{id}/{dato}")
    public ResponseEntity<?> changeStatus(@PathVariable Long id, @PathVariable String dato) {
        return ResponseEntity.status(HttpStatus.OK).body(service.changeStatus(id, dato));
    }

    @PostMapping("/find-date")
    public ResponseEntity<?> findDate(@RequestBody FechasDto fechas) {
        return ResponseEntity.status(HttpStatus.OK).body(service.findDate(fechas));
    }

    @GetMapping("/pdf/{reservaId}")
    public ResponseEntity<byte[]> getListStudentsPdf(@PathVariable Long reservaId) {
        byte[] contents = service.getListStudentsPdf(reservaId).toByteArray();
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.parseMediaType("application/pdf"));
        String filename = "factura.pdf";
        headers.setContentDispositionFormData(filename, filename);
        headers.setCacheControl("must-revalidate, post-check=0, pre-check=0");
        return new ResponseEntity<>(contents, headers, HttpStatus.OK);
    }
}
