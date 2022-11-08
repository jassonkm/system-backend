package com.system.permission.systembackend.domain.dto;

import com.system.permission.systembackend.domain.model.Evento;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.Set;

public class LocacionesDTO {
    private Integer idLocacion;

    @NotEmpty
    @Size(max = 50)
    private String nombre;

    @NotNull
    private Integer capacidad;

    private Set<Evento>eventos;

    public Set<Evento> getEventos() {
        return eventos;
    }

    public void setEventos(Set<Evento> eventos) {
        this.eventos = eventos;
    }

    public Integer getIdLocacion() {
        return idLocacion;
    }

    public void setIdLocacion(Integer idLocacion) {
        this.idLocacion = idLocacion;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Integer getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(Integer capacidad) {
        this.capacidad = capacidad;
    }

    public LocacionesDTO(){
        super();
    }
}
