package com.hotelharrison.api.utils;

import com.hotelharrison.api.model.*;
import com.hotelharrison.api.utils.other.*;

import java.util.ArrayList;
import java.util.List;

public class MapperUtil {

    public static HuespedDto HuespedUnique(Huesped h) {
        HuespedDto host = new HuespedDto();
        host.setId(h.getId());
        host.setNombre(h.getNombre());
        host.setApellido(h.getApellido());
        host.setDocumento(h.getDocumento());
        host.setCorreo(h.getCorreo());
        host.setTelefono(h.getTelefono());
        host.setEstado(h.getEstado());
        host.setTipoDocumento(h.getTipoDocumento());
        return host;
    }

    public static List<HuespedDto> Huespedes(List<Huesped> lH) {
        List<HuespedDto> lista = new ArrayList<>();
        for (Huesped huesped : lH) {
            lista.add(HuespedUnique(huesped));
        }
        return lista;
    }


    public static UsuarioDto UsuarioUnique(Usuario u) {
        UsuarioDto user = new UsuarioDto();
        user.setId(u.getId());
        user.setNombre(u.getNombre());
        user.setApellido(u.getApellido());
        user.setDni(u.getDni());
        user.setCorreo(u.getCorreo());
        user.setDireccion(u.getDireccion());
        user.setTelefono(u.getTelefono());
        user.setEstado(u.getEstado());
        user.setTipoUsuario(u.getTipoUsuario());
        return user;
    }

    public static List<UsuarioDto> Usuarios(List<Usuario> lU) {
        List<UsuarioDto> lista = new ArrayList<>();
        for (Usuario usuario : lU) {
            lista.add(UsuarioUnique(usuario));
        }
        return lista;
    }


    public static ReservaDto ReservaUnique(Reserva r){
        ReservaDto reserva = new ReservaDto();
        reserva.setId(r.getId());
        reserva.setFechaInicio(r.getFechaInicio());
        reserva.setFechaFinal(r.getFechaFinal());
        reserva.setPlacaVehiculo(r.getPlacaVehiculo());
        reserva.setCodigoReserva(r.getCodigoReserva());
        reserva.setCreadoEn(r.getCreadoEn());
        reserva.setIgv(r.getIgv());
        reserva.setDescuento(r.getDescuento());
        reserva.setPrecioTotal(r.getPrecioTotal());
        reserva.setPagoAdelantado(r.getPagoAdelantado());
        reserva.setEstado(r.getEstado());
        reserva.setUsuario(UsuarioUnique(r.getUsuario()));
        reserva.setHuesped(HuespedUnique(r.getHuesped()));
        reserva.setHabitacion(r.getHabitacion());
        reserva.setEstacionamiento(r.getEstacionamiento());
        return reserva;
    }

    public static List<ReservaDto> Reservas(List<Reserva> lR){
        List<ReservaDto> lista = new ArrayList<>();
        for (Reserva reserva : lR){
            lista.add(ReservaUnique(reserva));
        }
        return lista;
    }


    public static ConsumoDto ConsumoUnique(Consumo c){
        ConsumoDto consumo = new ConsumoDto();
        consumo.setId(c.getId());
        consumo.setCantidad(c.getCantidad());
        consumo.setTotal(c.getTotal());
        consumo.setReserva(ReservaUnique(c.getReserva()));
        consumo.setProducto(c.getProducto());
        return consumo;
    }

    public static List<ConsumoDto> Consumos(List<Consumo> lC){
        List<ConsumoDto> lista = new ArrayList<>();
        for (Consumo consumo : lC){
            lista.add(ConsumoUnique(consumo));
        }
        return lista;
    }


    public static FacturaDto FacturaUnique(Factura f){
        FacturaDto factura = new FacturaDto();
        factura.setId(f.getId());
        factura.setRuc(f.getRuc());
        factura.setRazonSocial(f.getRazonSocial());
        factura.setCreadoEn(f.getCreadoEn());
        factura.setLateCheckOut(f.getLateCheckOut());
        factura.setEstado(f.getEstado());
        factura.setReserva(ReservaUnique(f.getReserva()));
        return factura;
    }

    public static List<FacturaDto> Facturas(List<Factura> lF){
        List<FacturaDto> lista = new ArrayList<>();
        for (Factura factura : lF){
            lista.add(FacturaUnique(factura));
        }
        return lista;
    }

}
