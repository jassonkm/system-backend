package com.system.permission.systembackend.persistence.model;


import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "Evento")
public class Evento {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_evento")
    private Integer idEvento;


    private String nombre;


    @OneToOne(mappedBy = "evento",cascade = CascadeType.ALL,fetch = FetchType.LAZY)
    private Locaciones locaciones;

    public Locaciones getLocaciones() {
        return locaciones;
    }

    public void setLocaciones(Locaciones locaciones) {
        this.locaciones = locaciones;
    }

    private Date fecha;

    private Boolean estado= true;

    @Column(name = "n_asistentes")
    private String nAsistentes;

    private String clasificacion;

    private String qr;

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.LAZY,mappedBy = "evento")
    private Set<UsuarioEvento> usuarioEventos =new HashSet<>();

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

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

    public String getnAsistentes() {
        return nAsistentes;
    }

    public void setnAsistentes(String nAsistentes) {
        this.nAsistentes = nAsistentes;
    }

    public String getClasificacion() {
        return clasificacion;
    }

    public void setClasificacion(String clasificacion) {
        this.clasificacion = clasificacion;
    }

    public String getQr() {
        return qr;
    }

    public void setQr(String qr) {
        this.qr = qr;
    }

    public Set<UsuarioEvento> getUsuarioEventos() {
        return usuarioEventos;
    }

    public void setUsuarioEventos(Set<UsuarioEvento> usuarioEventos) {
        this.usuarioEventos = usuarioEventos;
    }
}
