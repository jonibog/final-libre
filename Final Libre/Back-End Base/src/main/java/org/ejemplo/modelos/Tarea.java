package org.ejemplo.modelos;

import com.fasterxml.jackson.annotation.JacksonInject;
import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Table(name = "tareas")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Tarea {
    @Id
    private String id;
    private String nombre;
    private String descripcion;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario autor;
    @OneToOne(fetch = FetchType.EAGER)
    private Usuario encargado;
    private Date fechaCreacion;
    private Date fechaRealizado;

    public void setFechaCreacion(Date date) {
    }

    public void setAutor(Usuario usuario) {
    }
        public void setNombre(String nombre) {
            this.nombre = nombre;
        }

    public void setId(String s) {
    }

    public void setEncargado(Class<? extends JacksonInject.Value> aClass) {
    }
}
