package com.system.permission.systembackend.services;

import com.system.permission.systembackend.domain.dto.EventoDTO;
import com.system.permission.systembackend.domain.model.Evento;

import java.util.List;

public interface EventoService {

    public boolean isNombre(String nombre);

    public Evento saveEvento(Integer idLocacion, EventoDTO eventoDTO);

    public List<Evento> findEvento();
    public Evento findByEventoId(Integer idEvento);

    public List<Evento> findEventoByNombre(String nombre);
    public List<Evento> findEventoByNAsistentes(String asistentes);

    public Evento updateEvento(EventoDTO eventoDTO, Integer idEvento);
    public Evento updateAsistentes(EventoDTO eventoDTO,Integer idEvento);

    public void deleteEvento(Integer idEvento, Integer idLocaciones);

}
