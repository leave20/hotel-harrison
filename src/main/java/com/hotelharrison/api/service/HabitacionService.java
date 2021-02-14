package com.hotelharrison.api.service;

import com.hotelharrison.api.config.error.exceptions.BadRequest;
import com.hotelharrison.api.config.error.exceptions.NotFound;
import com.hotelharrison.api.model.Habitacion;
import com.hotelharrison.api.model.Reserva;
import com.hotelharrison.api.repository.HabitacionRepository;
import com.hotelharrison.api.repository.NivelRepository;
import com.hotelharrison.api.repository.ReservaRepository;
import com.hotelharrison.api.repository.TipoHabitacionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class HabitacionService {

    @Autowired
    public HabitacionRepository repository;

    @Autowired
    private NivelRepository repositoryN;

    @Autowired
    private TipoHabitacionRepository repositoryTH;

    @Autowired
    private ReservaRepository repositoryR;

    public List<Habitacion> getAll(){
        List<Habitacion> lista = repository.findAll();
        if (lista.isEmpty()) throw new NotFound("No se encontraron registros de Habitaciones");

        estadoDia();

        return lista;
    }

    public Habitacion findById(Long id){
        if (!repository.existsById(id)) throw new NotFound("No se encontró Habitación con id: " + id);

        return repository.findById(id).orElse(null);
    }

    public Habitacion create(Habitacion habitacion) {
        if (habitacion.getNombre() == null) throw new BadRequest("Ingrese un Nombre para la Habitación");
        if (habitacion.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para la Habitación");
        habitacion.setNombre(habitacion.getNombre());

        Long nivelId = habitacion.getNivel().getId();
        if (nivelId == null) throw new BadRequest("Ingrese un Nivel");
        if (habitacion.getNivel() != null) {
            boolean nivel = repositoryN.existsById(nivelId);
            if (!nivel) throw new BadRequest("No se encontró Nivel con id: " + nivelId);
            habitacion.setNivel(habitacion.getNivel());
        }

        Long tipoHabitacionId = habitacion.getTipoHabitacion().getId();
        if (tipoHabitacionId == null) throw new BadRequest("Ingrese un Tipo de Habitación");
        if (habitacion.getTipoHabitacion() != null) {
            boolean tipoHabitacion = repositoryTH.existsById(tipoHabitacionId);
            if (!tipoHabitacion) throw new BadRequest("No se encontró Tipo de Habitación con id: " + tipoHabitacionId);
            habitacion.setTipoHabitacion(habitacion.getTipoHabitacion());
        }

        return repository.save(habitacion);
    }

    public Habitacion update(Habitacion habitacion, Long id) {
        Habitacion updHabitacion = findById(id);

        if (habitacion.getNombre() == null) throw new BadRequest("Ingrese un Nombre para la Habitación");
        if (habitacion.getNombre().isEmpty()) throw new BadRequest("Ingrese un Nombre para la Habitación");
        updHabitacion.setNombre(habitacion.getNombre());

        updHabitacion.setDescripcion(habitacion.getDescripcion());

        updHabitacion.setImagen(habitacion.getImagen());

        updHabitacion.setDescuento(habitacion.getDescuento());

        updHabitacion.setPromocion(habitacion.getPromocion());

        Long nivelId = habitacion.getNivel().getId();
        if (nivelId == null) throw new BadRequest("Ingrese un Nivel");
        if (habitacion.getNivel() != null) {
            boolean nivel = repositoryN.existsById(nivelId);
            if (!nivel) throw new BadRequest("No se encontró Nivel con id: " + nivelId);
            updHabitacion.setNivel(habitacion.getNivel());
        }

        Long tipoHabitacionId = habitacion.getTipoHabitacion().getId();
        if (tipoHabitacionId == null) throw new BadRequest("Ingrese un Tipo de Habitación");
        if (habitacion.getTipoHabitacion() != null) {
            boolean tipoHabitacion = repositoryTH.existsById(tipoHabitacionId);
            if (!tipoHabitacion) throw new BadRequest("No se encontró Tipo de Habitación con id: " + tipoHabitacionId);
            updHabitacion.setTipoHabitacion(habitacion.getTipoHabitacion());
        }

        return repository.save(updHabitacion);
    }

    public Habitacion changeStatus(Long id, String dato) {
        Habitacion updHabitacion = findById(id);
        if (dato == null) throw new BadRequest("Ingrese un Estado");
        if (dato.isEmpty()) throw new BadRequest("Ingrese un Estado");
        updHabitacion.setEstado(dato);

        return repository.save(updHabitacion);
    }

    public List<Habitacion> findByDisponible() {
        List<Habitacion> disponibles= repository.findByEstado("DISPONIBLE");
        if (disponibles.isEmpty()) throw new NotFound("No hay Habitaciones Disponibles");

        return disponibles;
    }

    public List<Habitacion> findByEstado(String estado) {
        List<Habitacion> habitaciones = repository.findByEstado(estado);
        if (habitaciones.isEmpty()) throw new NotFound("No hay Habitaciones con ese Estado.");

        return habitaciones;
    }

    public List<Habitacion> findByNroCamas(Integer nro){
        List<Habitacion> habitaciones = repository.findByTipoHabitacionNroCamas(nro);
        if (habitaciones.isEmpty()) throw new NotFound("No hay Habitaciones con el Número de Camas que desea.");

        return habitaciones;
    }

    public List<Habitacion> findByPromociones(){
        List<Habitacion> promociones = repository.findByPromocion(true);
        if (promociones.isEmpty()) throw new NotFound("No hay Promociones Disponibles.");

        return promociones;
    }

    public void estadoDia(){
        //metodo para cambiar el estado de las habitaciones de acuerdo al dia
        List<Reserva> reservas = repositoryR.findAll();
        for (Reserva res : reservas){
            Long idHabitacion = res.getHabitacion().getId();
            String estadoHabitacion = res.getEstado();

            if (estadoHabitacion.equals("ACTIVO")){
                changeStatus(idHabitacion, "OCUPADO");
            }
            //System.out.println("Hoy hay reservas: "+diaReserva);
        }
        //System.out.println("Hoy es: "+dia);
    }

}
