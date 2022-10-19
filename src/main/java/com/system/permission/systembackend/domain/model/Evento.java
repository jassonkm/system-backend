package com.system.permission.systembackend.domain.model;


import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDate;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@JsonIgnoreProperties({"hibernateLazyInitializer"})
@Entity
@Table(name = "Eventos",
        uniqueConstraints = {
        @UniqueConstraint(columnNames = "QR")
        })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class,property = "idEvento")
public class Evento implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    private String nombre;

    private Date fecha;

    @Column(name = "n_asistentes")
    private String nAsistentes;

    private String qr;

    @OneToMany(mappedBy = "evento",fetch = FetchType.LAZY,cascade = CascadeType.ALL,orphanRemoval = true)
    private Set<Locaciones> locaciones=new HashSet<>();

    public Evento() {
    }

    public Integer getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(Integer idEvento) {
        this.idEvento = idEvento;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getnAsistentes() {
        return nAsistentes;
    }

    public void setnAsistentes(String nAsistentes) {
        this.nAsistentes = nAsistentes;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public Set<Locaciones> getLocaciones() {
        return locaciones;
    }

    public void setLocaciones(Set<Locaciones> locaciones) {
        this.locaciones = locaciones;
    }

    public Evento(String nombre, Date fecha, String qr, Set<Locaciones> locaciones) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.qr = qr;
        this.locaciones = locaciones;
    }

    public Evento(String nAsistentes) {
        this.nAsistentes = nAsistentes;
    }
}
