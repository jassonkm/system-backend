package com.system.permission.systembackend.services.impl;

import com.system.permission.systembackend.domain.dto.LocacionesDTO;
import com.system.permission.systembackend.domain.model.Locaciones;
import com.system.permission.systembackend.repository.EventoRepository;
import com.system.permission.systembackend.repository.LocacionesRepository;
import com.system.permission.systembackend.services.LocacionesService;
import com.system.permission.systembackend.web.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class LocacionesServiceImpl implements LocacionesService {

    @Autowired
    private LocacionesRepository locacionesRepository;

    @Autowired
    private EventoRepository eventoRepository;


    @Override
    public boolean isNombre(String nombre) {
        return locacionesRepository.existsByNombre(nombre);
    }

    @Override
    public Locaciones addLocaciones(Locaciones locaciones) {
        locaciones= locacionesRepository.save(locaciones);
        Locaciones finalLocacion= locaciones;
        locaciones.getEvents().forEach(evento -> {
            evento.setLocations(finalLocacion);
            eventoRepository.save(evento);
        });
        return locaciones;
    }

    @Override
    @Transactional(readOnly = true)
    public List<Locaciones> findLocaciones() {
        return locacionesRepository.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public List<Locaciones> findLocacionesBynombre(String nombre) {
        return locacionesRepository.findByNombre(nombre);
    }

    @Override
    public Locaciones updateLocaciones(LocacionesDTO locacionesDTO, Integer idLocaciones) {
        Locaciones locaciones =locacionesRepository.findById(idLocaciones)
                .orElseThrow(()-> new ResourceNotFoundException("Locaciones","idLocaciones",idLocaciones));

        locaciones.setNombre(locacionesDTO.getNombre());
        locaciones.setCapacidad(locacionesDTO.getCapacidad());
        return locacionesRepository.save(locaciones);
    }

    @Override
    public void deleteLocaciones(Integer idLocaciones) {
        Locaciones locaciones= locacionesRepository.findById(idLocaciones)
                .orElseThrow(()-> new ResourceNotFoundException("Locaciones","idLocaciones",idLocaciones));
        locacionesRepository.delete(locaciones);
    }
}
