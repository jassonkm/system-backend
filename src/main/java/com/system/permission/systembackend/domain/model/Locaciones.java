package com.system.permission.systembackend.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;


@Entity
@Table(name = "locaciones")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLocacion")
public class Locaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacion")
    private Integer idLocacion;
    private String nombre;
    private Integer capacidad;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_evento")
    @JsonProperty(access = JsonProperty.Access.WRITE_ONLY) //Ignora la serializacion
    private Evento evento;

    public Locaciones(){}

    public Locaciones(String nombre, Integer capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;

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

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
