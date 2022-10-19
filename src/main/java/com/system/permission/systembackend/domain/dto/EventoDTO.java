package com.system.permission.systembackend.domain.dto;

import com.system.permission.systembackend.domain.model.Locaciones;

import javax.validation.constraints.*;
import java.time.LocalDate;
import java.util.Date;
import java.util.Set;

public class EventoDTO {

    private Integer idEvento;

    @NotEmpty
    @Size(max = 50)
    private String nombre;

    @NotNull
    private Date fecha;


    private String nAsistentes;

    @NotEmpty
    @Size(max =100)
    private String qr;

    private Set<Locaciones> locaciones;

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
    public EventoDTO(){
        super();
    }
}
