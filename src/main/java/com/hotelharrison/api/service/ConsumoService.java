package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Consumo;
import com.hotelharrison.api.repository.ConsumoRepository;
import com.hotelharrison.api.repository.ProductoRepository;
import com.hotelharrison.api.repository.ReservaRepository;
import com.hotelharrison.api.utils.MapperUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ConsumoService {

    @Autowired
    private ConsumoRepository repository;

    @Autowired
    private ReservaRepository repositoryR;

    @Autowired
    private ProductoRepository repositoryP;

    public List<?> getAll(){
        List<Consumo> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Detalles de Facturas");

        return MapperUtil.Consumos(lista);
    }

    public Consumo findById(Long id){
        if (!repository.existsById(id)) throw new NotFound("No se encontró Detalle de Factura con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Consumo create(Consumo consumo) {
        if (consumo.getCantidad() == null) throw new BadRequest("Ingrese la Cantidad");
        if (consumo.getCantidad() == 0) throw new BadRequest("Ingrese la Cantidad");
        consumo.setCantidad(consumo.getCantidad());

        if (consumo.getTotal() == null) throw new BadRequest("Ingrese el Precio");
        if (consumo.getTotal() == 0.0) throw new BadRequest("Ingrese el Precio");
        consumo.setTotal(consumo.getTotal());

        Long reservaId = consumo.getReserva().getId();
        if (reservaId == null) throw new BadRequest("Ingrese una Reserva");
        if (consumo.getReserva() != null) {
            boolean reserva = repositoryR.existsById(reservaId);
            if (!reserva) throw new BadRequest("No se encontró la Reserva con id: " + reservaId);
            consumo.setReserva(consumo.getReserva());
        }

        Long productoId = consumo.getProducto().getId();
        if (productoId == null) throw new BadRequest("Ingrese un Producto");
        if (consumo.getProducto() != null) {
            boolean producto = repositoryP.existsById(productoId);
            if (!producto) throw new BadRequest("No se encontró el Producto con id: " + reservaId);
            consumo.setProducto(consumo.getProducto());
        }

        return repository.save(consumo);
    }

    public Consumo update(Consumo consumo, Long id) {
        Consumo updConsumo = findById(id);

        if (consumo.getCantidad() == null) throw new BadRequest("Ingrese la Cantidad");
        if (consumo.getCantidad() == 0) throw new BadRequest("Ingrese la Cantidad");
        updConsumo.setCantidad(consumo.getCantidad());

        if (consumo.getTotal() == null) throw new BadRequest("Ingrese el Precio Total");
        if (consumo.getTotal() == 0.0) throw new BadRequest("Ingrese el Precio Total");
        updConsumo.setTotal(consumo.getTotal());

        Long reservaId = consumo.getReserva().getId();
        if (reservaId == null) throw new BadRequest("Ingrese una Reserva");
        if (consumo.getReserva() != null) {
            boolean reserva = repositoryR.existsById(reservaId);
            if (!reserva) throw new BadRequest("No se encontró la Reserva con id: " + reservaId);
            updConsumo.setReserva(consumo.getReserva());
        }

        Long productoId = consumo.getProducto().getId();
        if (productoId == null) throw new BadRequest("Ingrese un Producto");
        if (consumo.getProducto() != null) {
            boolean producto = repositoryP.existsById(productoId);
            if (producto) throw new BadRequest("No se encontró el Producto con id: " + reservaId);
            updConsumo.setProducto(consumo.getProducto());
        }

        return repository.save(updConsumo);
    }

    public List<?> findbyReserva(Long id){
        List<Consumo> listaReservaId = repository.findByReservaId(id);
        if (listaReservaId.isEmpty()) throw new NotFound("No se encontraron registros de Consumos");

        return MapperUtil.Consumos(listaReservaId);
    }
}
