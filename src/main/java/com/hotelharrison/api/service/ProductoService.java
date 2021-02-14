package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Producto;
import com.hotelharrison.api.repository.ProductoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class ProductoService {

    @Autowired
    private ProductoRepository repository;

    public List<Producto> getAll() {
        List<Producto> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Productos");

        return lista;
    }

    public Producto findById(Long id) {
        if (!repository.existsById(id)) throw new NotFound("No se encontró Producto con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Producto create(Producto producto) {
        if (producto.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Producto");
        if (producto.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Producto");
        producto.setNombre(producto.getNombre());

        if (producto.getPrecio() == null) throw new BadRequest("Ingrese un precio");
        if (producto.getPrecio() < 0) throw new BadRequest("Ingrese el Precio mayor a 0");
        producto.setPrecio(producto.getPrecio());

        if (producto.getStock() == null) throw new BadRequest("Ingrese el Stock");
        if (producto.getStock() < 0) throw new BadRequest("Ingrese el Stock mayor o igual a 0");
        producto.setStock(producto.getStock());

        return repository.save(producto);
    }

    public Producto update(Producto producto, Long id) {
        Producto updProducto = findById(id);

        if (producto.getNombre() == null) throw new BadRequest("Ingrese un Nombre para el Producto");
        if (producto.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para el Producto");
        updProducto.setNombre(producto.getNombre());

        if (producto.getPrecio() == null) throw new BadRequest("Ingrese un precio");
        if (producto.getPrecio() < 0) throw new BadRequest("Ingrese el Precio mayor a 0");
        updProducto.setPrecio(producto.getPrecio());

        if (producto.getStock() == null) throw new BadRequest("Ingrese el Stock");
        if (producto.getStock() < 0) throw new BadRequest("Ingrese el Stock mayor o igual a 0");
        updProducto.setStock(producto.getStock());

        return repository.save(updProducto);
    }
}
