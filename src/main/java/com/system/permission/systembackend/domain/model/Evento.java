package com.system.permission.systembackend.domain.model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table(name = "Eventos",
    uniqueConstraints = {
            @UniqueConstraint(columnNames = "QR")
    })
@JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "idEvento")
public class Evento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;

    private String nombre;

    private Date fecha;

    @Column(name = "n_asistentes")
    private String nAsistentes;

    private String qr;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "idLocacion")
    private Locaciones locaciones;

    public Evento(){}

    public Evento(String nombre, Date fecha, String nAsistentes, String qr) {
        this.nombre = nombre;
        this.fecha = fecha;
        this.nAsistentes = nAsistentes;
        this.qr = qr;
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

    public Locaciones getLocations() {
        return locaciones;
    }

    public void setLocations(Locaciones locaciones) {
        this.locaciones = locaciones;
    }
    public Evento(String nAsistentes){ this.nAsistentes=nAsistentes;}
}
