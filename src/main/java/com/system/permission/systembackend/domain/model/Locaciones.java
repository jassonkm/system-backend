package com.system.permission.systembackend.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "locaciones")
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idLocacion")
public class Locaciones implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacion")
    private Integer idLocacion;

    private String nombre;

    private Integer capacidad;

    @OneToMany(mappedBy = "locaciones",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Evento> events= new HashSet<>();

    public Locaciones(){}

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

    public Set<Evento> getEvents() {
        return events;
    }

    public void setEvents(Set<Evento> events) {
        this.events = events;
    }

    public Locaciones(String nombre, Integer capacidad, Set<Evento> events) {
        this.nombre = nombre;
        this.capacidad = capacidad;
        this.events = events;
    }
}
