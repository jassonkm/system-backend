package com.system.permission.systembackend.web.payload.response;

import lombok.Data;

@Data
public class LocacionesResponse {
    private String nombre;
    private Integer capacidad;

    public LocacionesResponse(String nombre, Integer capacidad) {
        this.nombre = nombre;
        this.capacidad = capacidad;
    }
}
