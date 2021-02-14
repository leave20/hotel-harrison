package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Habitacion;
import com.hotelharrison.api.model.Reserva;
import com.hotelharrison.api.model.Usuario;
import com.hotelharrison.api.repository.*;
import com.hotelharrison.api.utils.MapperUtil;
import com.hotelharrison.api.utils.other.CodigoReserva;
import com.hotelharrison.api.utils.other.FechasDto;
import com.hotelharrison.api.utils.other.ReservaDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
@Transactional
public class ReservaService {

    @Autowired
    private ReservaRepository repository;

    @Autowired
    private UsuarioRepository repositoryU;

    @Autowired
    private HuespedRepository repositoryHu;

    @Autowired
    private HabitacionRepository repositoryHa;

    @Autowired
    private HabitacionService serviceHa;

    @Autowired
    private EstacionamientoRepository repositoryE;

    public List<?> getAll(){
        List<Reserva> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Reservas");

        return MapperUtil.Reservas(lista);
    }

    public Reserva findById(Long id) {
        if (!repository.existsById(id)) throw new NotFound("No se encontró Reserva con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Reserva create(Reserva reserva) {
        if (reserva.getFechaInicio() == null) throw new BadRequest("Ingrese una Fecha de Inicio");
        reserva.setFechaInicio(reserva.getFechaInicio());

        if (reserva.getFechaFinal() == null) throw new BadRequest("Ingrese una Fecha Final");
        reserva.setFechaFinal(reserva.getFechaFinal());

        if (reserva.getPlacaVehiculo() == null) reserva.setPlacaVehiculo("NO TIENE");
        reserva.setPlacaVehiculo(reserva.getPlacaVehiculo());

        if (reserva.getUsuario() == null){
            Usuario usuario = repositoryU.findById(2L).orElse(null);
            reserva.setUsuario(usuario);
        }else {
            Long usuarioId = reserva.getUsuario().getId();
            if (usuarioId == null) throw new BadRequest("Ingrese un Usuario");
            if (reserva.getUsuario() != null) {
                boolean usuario = repositoryU.existsById(usuarioId);
                if (!usuario) throw new BadRequest("No se encontró Usuario con id: " + usuarioId);
                reserva.setUsuario(reserva.getUsuario());
            }
        }

        Long huespedId = reserva.getHuesped().getId();
        if (huespedId == null) throw new BadRequest("Ingrese un Huesped");
        if (reserva.getHuesped() != null) {
            boolean huesped = repositoryHu.existsById(huespedId);
            if (!huesped) throw new BadRequest("No se encontró Huesped con id: " + huespedId);
            reserva.setHuesped(reserva.getHuesped());
        }

        if (reserva.getPrecioTotal() == null) throw new BadRequest("Ingrese el Precio Total");
        if (reserva.getPrecioTotal() < 0) throw new BadRequest("Ingrese el Precio Total mayor a 0");
        Double igv = reserva.getPrecioTotal() * 0.18;
        reserva.setIgv(igv);
        reserva.setPrecioTotal(reserva.getPrecioTotal());

        Long habitacionId = reserva.getHabitacion().getId();
        if (habitacionId == null) throw new BadRequest("Ingrese una Habitación");
        if (reserva.getHabitacion() != null) {
            boolean habitacion = repositoryHa.existsById(habitacionId);
            if (!habitacion) throw new BadRequest("No se encontró habitación con id: " + habitacionId);
            reserva.setHabitacion(reserva.getHabitacion());
        }

        if (reserva.getEstacionamiento() == null) reserva.setEstacionamiento(null);
        else {
            Long estacionamientoId = reserva.getEstacionamiento().getId();
            boolean estacionamiento = repositoryE.existsById(estacionamientoId);
            if (estacionamiento) reserva.setEstacionamiento(reserva.getEstacionamiento());
            else reserva.setEstacionamiento(null);
        }

        /**/
        CodigoReserva codigoReserva = new CodigoReserva();
        String codigo = codigoReserva.generarCodigo();
        boolean cod = repository.existsByCodigoReserva(codigo);
        if (cod){
            String codigotwo = codigoReserva.generarCodigo();
            boolean codtwo = repository.existsByCodigoReserva(codigotwo);
            if (codtwo){
                String codigothree = codigoReserva.generarCodigo();
                boolean codthree = repository.existsByCodigoReserva(codigothree);
                if (codthree){
                    String codigofour = codigoReserva.generarCodigo();
                    reserva.setCodigoReserva(codigofour);
                    System.out.println("4 : "+codigofour);

                }
                else reserva.setCodigoReserva(codigothree);
                System.out.println("3 : "+codigothree);

            }else reserva.setCodigoReserva(codigotwo);
            System.out.println("2 : "+codigotwo);

        }else reserva.setCodigoReserva(codigo);
        System.out.println("1 : "+codigo);


        //si la reserva se hace hoy se cambia el estado automaticamente
        LocalDate hoy = LocalDate.now();
        if(reserva.getFechaInicio().isEqual(hoy)) {
            Habitacion habitacion = reserva.getHabitacion();
            Long idHab = habitacion.getId();
            serviceHa.changeStatus(idHab,"RESERVADO");
        }

        return repository.save(reserva);
    }

    public Reserva update(Reserva reserva, Long id) {
        Reserva updReserva = findById(id);

        if (reserva.getFechaInicio() == null) throw new BadRequest("Ingrese una Fecha de Inicio");
        updReserva.setFechaInicio(reserva.getFechaInicio());

        if (reserva.getFechaFinal() == null) throw new BadRequest("Ingrese una Fecha Final");
        updReserva.setFechaFinal(reserva.getFechaFinal());

        if (reserva.getPlacaVehiculo() == null) updReserva.setPlacaVehiculo("No Tiene");
        updReserva.setPlacaVehiculo(reserva.getPlacaVehiculo());

        if (reserva.getIgv() == null) throw new BadRequest("Ingrese el IGV");
        if (reserva.getIgv() < 0) throw new BadRequest("Ingrese el IGV mayor a 0");
        updReserva.setIgv(reserva.getIgv());

        if (reserva.getDescuento() == null) throw new BadRequest("Ingrese el Descuento");
        if (reserva.getDescuento() < 0) throw new BadRequest("Ingrese el Descuento mayor a 0");
        updReserva.setDescuento(reserva.getDescuento());

        if (reserva.getPrecioTotal() == null) throw new BadRequest("Ingrese el Precio Total");
        if (reserva.getPrecioTotal() < 0) throw new BadRequest("Ingrese el Precio Total mayor a 0");
        updReserva.setPrecioTotal(reserva.getPrecioTotal());

        updReserva.setPagoAdelantado(reserva.getPagoAdelantado());

        Long usuarioId = reserva.getUsuario().getId();
        if (usuarioId == null) throw new BadRequest("Ingrese un Usuario");
        if (reserva.getUsuario() != null) {
            boolean usuario = repositoryU.existsById(usuarioId);
            if (!usuario) throw new BadRequest("No se encontró Usuario con id: " + usuarioId);
            updReserva.setUsuario(reserva.getUsuario());
        }

        Long huespedId = reserva.getHuesped().getId();
        if (huespedId == null) throw new BadRequest("Ingrese un Huesped");
        if (reserva.getHuesped() != null) {
            boolean huesped = repositoryHu.existsById(huespedId);
            if (!huesped) throw new BadRequest("No se encontró Huesped con id: " + huespedId);
            updReserva.setHuesped(reserva.getHuesped());
        }

        Long habitacionId = reserva.getHabitacion().getId();
        if (habitacionId == null) throw new BadRequest("Ingrese una Habitación");
        if (reserva.getHabitacion() != null) {
            boolean habitacion = repositoryHa.existsById(habitacionId);
            if (!habitacion) throw new BadRequest("No se encontró habitación con id: " + habitacionId);
            updReserva.setHabitacion(reserva.getHabitacion());
        }

        Long estacionamientoId = reserva.getEstacionamiento().getId();
        boolean estacionamiento = repositoryE.existsById(estacionamientoId);
        if (estacionamiento) updReserva.setEstacionamiento(reserva.getEstacionamiento());
        else updReserva.setEstacionamiento(null);

        return repository.save(updReserva);
    }

    public Reserva changeStatus(Long id, String dato) {
        Reserva updReserva = findById(id);

        if (dato == null) throw new BadRequest("Ingrese un Estado");
        if (dato.isEmpty()) throw new BadRequest("Ingrese un Estado");
        updReserva.setEstado(dato);

        Habitacion habitacion = updReserva.getHabitacion();
        Long idHab = habitacion.getId();
        String estadoHab;

        if (dato.toUpperCase().equals("ACTIVO")) {
            estadoHab = "OCUPADO";
            serviceHa.changeStatus(idHab,estadoHab);

        }
        if (dato.toUpperCase().equals("FINALIZADO")){
            estadoHab = "LIMPIEZA";
            serviceHa.changeStatus(idHab,estadoHab);
        }

        return repository.save(updReserva);
    }

    public List<?> findDate(FechasDto fechas) {
        LocalDateTime start = fechas.getStart().atStartOfDay();
        LocalDateTime finish = fechas.getFinish().atStartOfDay();

        if (fechas.getFinish() == null) finish = LocalDateTime.now();

        List<Reserva> recervasXfechas = repository.findByCreadoEnBetween(start, finish);
        if (recervasXfechas.isEmpty()) throw new NotFound("No se encontraron registros de Reservas entre esas fechas");

        return MapperUtil.Reservas(recervasXfechas);
    }

    public List<?> findByHuesped(Long id) {
        List<Reserva> listaHuespedId = repository.findByHuespedId(id);
        if (listaHuespedId.isEmpty()) throw new NotFound("No se encontraron registros de Reservas");

        return MapperUtil.Reservas(listaHuespedId);
    }

    public List<?> findByBusyDateTime(FechasDto fechas){
        LocalDate start = fechas.getStart();
        LocalDate finish = fechas.getFinish();
        List<?> habitaciones;

        if (start == null) throw new BadRequest("Ingrese una Fecha de Inicio");
        if (finish == null) throw new BadRequest("Ingrese una Fecha Final");

        List<Reserva> reservasXDiaInicio = repository.findByFechaInicioBetween(start, finish);
        //System.out.println("ocupados dia inicio : "+reservasXDiaInicio.size());

        if (reservasXDiaInicio.isEmpty()){
            habitaciones = repositoryHa.findAll();
            //System.out.println("NO HAY RESERVACIONES");
        }else {
            List<Long> habOcupadas = new ArrayList<>();
            for (Reserva habReserva : reservasXDiaInicio){
                Long idHabOcupadas;
                idHabOcupadas = habReserva.getHabitacion().getId();
                habOcupadas.add(idHabOcupadas);
            }
            habitaciones = repositoryHa.findByIdNotIn(habOcupadas);
            //System.out.println("todas las habitaciones menos : "+habOcupadas);
        }
        return habitaciones;
    }

    public ReservaDto findByCodigoReserva(String codigo, Long idHabitacion) {
        Reserva reservaxcodigo = repository.findByCodigoReserva(codigo);
        if (reservaxcodigo == null) throw new NotFound("No se encontró la reserva con el código: "+codigo);
        if (!reservaxcodigo.getHabitacion().getId().equals(idHabitacion)) throw new NotFound("'El código '"+codigo + "' no pertenece a está habitación");
        return MapperUtil.ReservaUnique(reservaxcodigo);
    }

    public ReservaDto findByHabitacion(Long id){
        List<Reserva> reservaXhabitacion = repository.findByHabitacionId(id);

        for (Reserva reserva : reservaXhabitacion){
            Long idReserva = reserva.getId();
            String estadoReserva = reserva.getEstado();

            if (estadoReserva.equals("ACTIVO")){
                return MapperUtil.ReservaUnique(findById(idReserva));
            }

        }
        throw new BadRequest("No hay Reservas Activas con esa Habitación");
    }

}
