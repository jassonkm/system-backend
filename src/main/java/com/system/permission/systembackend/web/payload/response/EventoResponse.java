package com.system.permission.systembackend.web.payload.response;


import lombok.Data;

import java.time.LocalDate;
import java.util.Date;

@Data
public class EventoResponse {
    private String nombre;

    private String asistentes;

    private Date fecha;

    public EventoResponse(String nombre, Date fecha) {
        this.nombre = nombre;
        this.fecha = fecha;
    }
    public EventoResponse(String asistentes){
        this.asistentes=asistentes;
    }
}
