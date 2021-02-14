package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Consumo;
import com.hotelharrison.api.model.Factura;
import com.hotelharrison.api.repository.ConsumoRepository;
import com.hotelharrison.api.repository.FacturaRepository;
import com.hotelharrison.api.repository.ReservaRepository;
import com.hotelharrison.api.utils.MapperUtil;
import com.hotelharrison.api.utils.other.FechasDto;
import com.hotelharrison.api.view.pdf.FacturaPdf;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.util.List;

@Service
@Transactional
public class FacturaService {

    @Autowired
    private FacturaRepository repository;

    @Autowired
    private ReservaRepository repositoryR;

    @Autowired
    private ConsumoRepository consumoRepository;

    public List<?> getAll(){
        List<Factura> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Facturas");

        return MapperUtil.Facturas(lista);
    }

    public Factura findById(Long id){
        if (!repository.existsById(id)) throw new NotFound("No se encontró Factura con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Factura create(Factura factura) {
        if (factura.getRuc().isEmpty()) factura.setRuc(null);
        if (factura.getRuc() == null) factura.setRuc(null);
        else {
            if (factura.getRuc().length() < 11)
                throw new BadRequest("Ingrese Correctamente el RUC(11 digitos)");
            if (factura.getRuc().length() > 11)
                throw new BadRequest("Ingrese Correctamente el RUC(11 digitos)");
            factura.setRuc(factura.getRuc());
        }

        if (factura.getRazonSocial() == null) throw new BadRequest("Ingrese la Razón Social");
        if (factura.getRazonSocial().isEmpty()) throw new BadRequest("Ingrese la Razón Social");
        factura.setRazonSocial(factura.getRazonSocial());

        Long reservaId = factura.getReserva().getId();
        if (reservaId == null) throw new BadRequest("Ingrese una Reserva");
        if (factura.getReserva() != null) {
            boolean reserva = repositoryR.existsById(reservaId);
            if (!reserva) throw new BadRequest("No se encontró Reserva con id: " + reservaId);
            factura.setReserva(factura.getReserva());
        }

        return repository.save(factura);
    }

    public Factura update(Factura factura, Long id) {
        Factura updFactura = findById(id);

        if (factura.getRuc().isEmpty()) updFactura.setRuc(null);
        if (factura.getRuc() == null) updFactura.setRuc(null);
        else {
            if (factura.getRuc().length() < 11)
                throw new BadRequest("Ingrese Correctamente el RUC(11 digitos)");
            if (factura.getRuc().length() > 11)
                throw new BadRequest("Ingrese Correctamente el RUC(11 digitos)");
            updFactura.setRuc(factura.getRuc());
        }

        if (factura.getRazonSocial() == null) throw new BadRequest("Ingrese la Razón Social");
        if (factura.getRazonSocial().isEmpty()) throw new BadRequest("Ingrese la Razón Social");
        updFactura.setRazonSocial(factura.getRazonSocial());

        updFactura.setLateCheckOut(factura.getLateCheckOut());

        Long reservaId = factura.getReserva().getId();
        if (reservaId == null) throw new BadRequest("Ingrese una Reserva");
        if (factura.getReserva() != null) {
            boolean reserva = repositoryR.existsById(reservaId);
            if (!reserva) throw new BadRequest("No se encontró Reserva con id: " + reservaId);
            updFactura.setReserva(factura.getReserva());
        }

        return repository.save(updFactura);
    }

    public Factura changeStatus(Long id, String dato) {
        Factura updFactura = findById(id);

        if (dato == null) throw new BadRequest("Ingrese un Estado");
        if (dato.isEmpty()) throw new BadRequest("Ingrese un Estado");
        updFactura.setEstado(dato);

        return repository.save(updFactura);
    }

    public List<?> findDate(FechasDto fechas) {
        LocalDateTime start = fechas.getStart().atStartOfDay();
        LocalDateTime finish = fechas.getFinish().atStartOfDay();
        if (fechas.getFinish() == null) finish = LocalDateTime.now();

        List<Factura> facturaXfechas = repository.findByCreadoEnBetween(start, finish);
        if (facturaXfechas.isEmpty()) throw new NotFound("No se encontraron registros de Facturas entre esas fechas");

        return MapperUtil.Facturas(facturaXfechas);
    }

    public ByteArrayOutputStream getListStudentsPdf(Long reservaId) {
        List<Consumo> consumos = consumoRepository.findByReservaId(reservaId);
        FacturaPdf facturaPdf = new FacturaPdf();
        Factura factura  = repository.findByReservaId(reservaId);
        return facturaPdf.getFacturaPdf(factura, consumos);
    }
}
