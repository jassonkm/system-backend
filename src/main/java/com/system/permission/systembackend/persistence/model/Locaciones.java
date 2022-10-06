package com.system.permission.systembackend.persistence.model;


import javax.persistence.*;

@Entity
@Table(name = "locaciones")
public class Locaciones {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_locacion")
    private Integer idLocacion;
    private String nombre;
    private String capacidad;

    @OneToOne(fetch = FetchType.LAZY)
    @JoinColumn(name="id_evento")
    private Evento evento;

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

    public String getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(String capacidad) {
        this.capacidad = capacidad;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
