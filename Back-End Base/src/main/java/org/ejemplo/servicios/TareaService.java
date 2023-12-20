package org.ejemplo.servicios;

import org.ejemplo.exceptions.BalanceException;
import org.ejemplo.modelos.Tarea;
import org.ejemplo.modelos.Usuario;
import org.ejemplo.repository.TareaRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class TareaService {
    private TareaRepository tareaRepository;
    private UsersService usersService;
private tareavalitaion tareavalidation;

    TareaService(UsersService usersService, TareaRepository tareaRepository, tareavalition tareavalidation){
        this.usersService = usersService;
        this.tareaRepository = tareaRepository;
        this.tareavalidation = tareavalidation;
    }


    public Tarea crearTarea(String user, Tarea tarea) throws Tareaexception {
        try {
            tareavalidation.validateTareaParaCrear(user, tarea);
            tarea.setFechaCreacion(new Date());
            Usuario usuario = usersService.findByUser(user).get();
            tarea.setAutor(usuario);
            tarea.setId(user + UUID.randomUUID());
            return tareaRepository.save(tarea);
        } catch (TareaException ) {
            throw new Tareaexception(HttpStatus.INTERNAL_SERVER_ERROR , "No se puede crear la tarea", "Crear tarea" );
        }
    }

        Tarea tareaRegistrada = tareaRepository.findById(tarea.getId()).get();
        tareaRegistrada.setNombre(tarea.getNombre());
        tareaRegistrada.setDescripcion(tarea.getDescripcion());
        if (usuario.getRole().equalsIgnoreCase("administrador") || tarea.getEncargado() != null){
            tareaRegistrada.setEncargado(tarea.getEncargado());
        }
        return tareaRepository.save(tareaRegistrada);
    }

    public List<Tarea> obtenerTodos() {
        try {
            return tareaRepository.findAll();
        } catch (TareaException ) {
            throw new Tareaexception(HttpStatus.INTERNAL_SERVER_ERROR, "Error al obtener todas las tareas", "Obtener todas las tareas");
        }
    }


    public List<Tarea> findByEncargado(String user) {
        try {
            return tareaRepository.findAll()
        } catch (TareaException ) {
            throw new Tareaexception(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar tareas por encargado", "Buscar tareas por encargado");
        }
    }


    public List<Tarea> findByAutor(String user) {
        try {
            return tareaRepository.findAll()
        } catch (TareaException ) {
            throw new Tareaexception(HttpStatus.INTERNAL_SERVER_ERROR, "Error al buscar tareas por autor", "Buscar tareas por autor");
        }
    }


    public Tarea actualizarTarea(String user, Tarea tarea) {
        try {
            tareaValidation.validateActualizarTarea(user, tarea);
            Usuario usuario = usersService.findByUser(user).get();
            Tarea tareaRegistrada = tareaRepository.findById(tarea.getId()).get();
            tareaRegistrada.setNombre(tarea.getNombre());
            tareaRegistrada.setDescripcion(tarea.getDescripcion());
            if (usuario.getRole().equalsIgnoreCase("administrador") || tarea.getEncargado() != null) {
                tareaRegistrada.setEncargado(tarea.getEncargado());
            }
            return tareaRepository.save(tareaRegistrada);
        } catch (TareaException ) {
            throw new Tareaexception(HttpStatus.INTERNAL_SERVER_ERROR, "Error al actualizar la tarea", "Actualizar tarea");
        }
    }

    public Tarea cerrarTarea(String user, String id) {
        try {
            tareavalidation.validateCerrarTarea(user, id);
            Tarea tareaRegistrada = tareaRepository.findById(id).get();
            Usuario usuario = usersService.findByUser(user).get();
            tareaRegistrada.setFechaRealizado(new Date());
            return tareaRepository.save(tareaRegistrada);
        } catch (TareaException) {
            throw new Tareaexception(HttpStatus.INTERNAL_SERVER_ERROR, "Error al cerrar la tarea", "Cerrar tarea");
        }
    }
}
