package com.system.permission.systembackend.services;

import com.system.permission.systembackend.domain.dto.LocacionesDTO;
import com.system.permission.systembackend.domain.model.Locaciones;

import java.util.List;

public interface LocacionesService {
    public boolean isNombre(String nombre);

    public Locaciones addLocaciones(Integer idEvento, LocacionesDTO locacionesDTO);

    public List<Locaciones> findLocaciones();

    public List<Locaciones> findLocacionesBynombre(String nombre);

    public Locaciones updateLocaciones(LocacionesDTO locacionesDTO, Integer idLocaciones);

    public void deleteLocaciones(Integer idEvento,Integer idLocaciones);
}
