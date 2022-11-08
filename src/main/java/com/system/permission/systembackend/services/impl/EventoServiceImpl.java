package com.system.permission.systembackend.services.impl;

import com.system.permission.systembackend.domain.dto.EventoDTO;
import com.system.permission.systembackend.domain.model.Evento;
import com.system.permission.systembackend.domain.model.Locaciones;
import com.system.permission.systembackend.repository.EventoRepository;
import com.system.permission.systembackend.repository.LocacionesRepository;
import com.system.permission.systembackend.services.EventoService;
import com.system.permission.systembackend.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.system.permission.systembackend.web.exceptions.AppException;

import java.util.List;

@Service
public class EventoServiceImpl implements EventoService {



    @Autowired
    private EventoRepository eventoRepository;

    @Autowired
    LocacionesRepository locacionesRepository;



    @Override
    public boolean isNombre(String nombre) {
        return eventoRepository.existsBynombre(nombre);
    }

    @Override
    public Evento saveEvento(Integer idLocacion, EventoDTO eventoDTO) {
        Evento evento= new Evento(
                eventoDTO.getNombre(),
                eventoDTO.getFecha(),
                eventoDTO.getnAsistentes(),
                eventoDTO.getQr());
        Locaciones locaciones = locacionesRepository.findById(idLocacion)
                .orElseThrow(()-> new ResourceNotFoundException("Locacion","idLocacion",idLocacion));
        evento.setLocations(locaciones);
        return eventoRepository.save(evento);
    }


    @Override
    public List<Evento> findEvento() {
        return eventoRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Evento findByEventoId(Integer idEvento) {
        return eventoRepository.findById(idEvento).orElseThrow(()-> new ResourceNotFoundException("Evento","idEvento",idEvento));
    }

    @Override
    public List<Evento> findEventoByNombre(String nombre) {
        return eventoRepository.findByNombre(nombre);
    }

    @Override
    public List<Evento> findEventoByNAsistentes(String asistentes) {
        return eventoRepository.findBynAsistentes(asistentes);
    }

    @Override
    public Evento updateEvento(EventoDTO eventoDTO, Integer idEvento) {
        Evento evento=eventoRepository.findById(idEvento).orElseThrow(()-> new ResourceNotFoundException("Evento","idEvento",idEvento));

        evento.setNombre(eventoDTO.getNombre());
        evento.setFecha(eventoDTO.getFecha());
        evento.setQr(evento.getQr());
        return eventoRepository.save(evento);
    }

    @Override
    public Evento updateAsistentes(EventoDTO eventoDTO, Integer idEvento) {
        Evento evento=eventoRepository.findById(idEvento).orElseThrow(()-> new ResourceNotFoundException("Evento","idEvento",idEvento));

        evento.setnAsistentes(eventoDTO.getnAsistentes());
        return eventoRepository.save(evento);
    }

    @Override
    public void deleteEvento(Integer idEvento, Integer idLocacion) {
        Locaciones locaciones = locacionesRepository.findById(idLocacion)
                .orElseThrow(()-> new ResourceNotFoundException("Locacion","idLocacion",idLocacion));
        Evento evento= eventoRepository.findById(idEvento)
                .orElseThrow(()-> new ResourceNotFoundException("Evento","idEvento",idEvento));

        eventoRepository.delete(evento);
    }
}
