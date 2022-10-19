package com.system.permission.systembackend.services.impl;

import com.system.permission.systembackend.domain.dto.EventoDTO;
import com.system.permission.systembackend.domain.model.Evento;
import com.system.permission.systembackend.repository.EventoRepository;
import com.system.permission.systembackend.repository.LocacionesRepository;
import com.system.permission.systembackend.services.EventoService;
import com.system.permission.systembackend.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

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
    public Evento saveEvento(Evento evento) {
        evento=eventoRepository.save(evento);
        Evento finalEvento=evento;
        evento.getLocaciones().forEach(locaciones -> {
            locaciones.setEvento(finalEvento);
            locacionesRepository.save(locaciones);
        });
        return evento;
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
    public void deleteEvento(Integer idEvento) {
        Evento evento=eventoRepository.findById(idEvento)
                .orElseThrow(()-> new ResourceNotFoundException("Evento","idEvento",idEvento));
        eventoRepository.delete(evento);
    }
}
