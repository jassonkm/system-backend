package com.system.permission.systembackend.web.controllers;

import com.system.permission.systembackend.domain.dto.EventoDTO;
import com.system.permission.systembackend.domain.model.Evento;
import com.system.permission.systembackend.services.EventoService;
import com.system.permission.systembackend.web.payload.response.EventoResponse;
import com.system.permission.systembackend.web.payload.response.MessageResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/eventos")
public class EventoController {

    @Autowired
    private EventoService eventoService;

    @Autowired
    HttpServletRequest requestEvento;

    private static final Logger logger= LoggerFactory.getLogger(EventoController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?>createEvento(@Valid @RequestBody Evento evento){
        if (eventoService.isNombre(evento.getNombre())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Este nombre de evento ya esta en uso"));
        }
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(eventoService.saveEvento(evento));
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse(("El evento ya existe")));
        }
    }
    @PreAuthorize("hasRol('USER')")
    @GetMapping("/listar")
    public ResponseEntity<?> getEventos(@Valid @RequestParam(required = false, value = "idEVento")Integer idEvento){
        try{
            if (idEvento !=null){
                Evento evento= eventoService.findByEventoId(idEvento);
                return ResponseEntity
                        .ok()
                        .body(evento);
            }
            else {
                return ResponseEntity
                        .ok()
                        .body(eventoService.findEvento());
            }
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("El evento con id "+idEvento+ " no existe"));
        }
    }
    @PreAuthorize("hasRol('USER')")
    @GetMapping("/nameEventos")
    public ResponseEntity<?> getEventoByName(String name){
        try {
                List<Evento> eventosByName = eventoService.findEventoByNombre(name);
                List<EventoResponse> eventoResponses = new ArrayList<>();
                for (Evento evento : eventosByName) {
                    EventoResponse eventoResponse = new EventoResponse(evento.getNombre(),evento.getFecha());
                    eventoResponses.add(eventoResponse);
                }
                if (!eventosByName.isEmpty()) {
                    return ResponseEntity
                            .ok()
                            .body(eventoResponses);
                } else {
                    return ResponseEntity
                            .status(HttpStatus.NOT_FOUND)
                            .body(new MessageResponse("No se encontró el evento con nombre"+name));

                }
        } catch (Exception exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El evento no existe"));
        }
    }
    @PreAuthorize("hasRol('USER')")
    @GetMapping("/asistentes")
    public ResponseEntity<?> getEventoByAsitentes(String asistentes){
        try {
            List<Evento> eventosByName = eventoService.findEventoByNAsistentes(asistentes);
            List<EventoResponse> eventoResponses = new ArrayList<>();
            for (Evento evento : eventosByName) {
                EventoResponse eventoResponse = new EventoResponse(evento.getnAsistentes());
                eventoResponses.add(eventoResponse);            }
            if (!eventosByName.isEmpty()) {
                return ResponseEntity
                        .ok()
                        .body(eventoResponses);
            } else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new MessageResponse("No se encontró el evento con nombre"+asistentes));

            }
        } catch (Exception exception) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: El evento no existe"));
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idEvento}")
    public ResponseEntity<?> updateEvento(@Valid @RequestBody EventoDTO eventoDTO, @PathVariable(value = "idEvento")Integer idEvento){
        try {
            Evento eventoRespose= eventoService.updateEvento(eventoDTO,idEvento);
            return ResponseEntity
                    .ok()
                    .body(eventoRespose);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No se encontro el evento con id "+idEvento+"no existe, para actualizar"));
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/updateAsis/{idEvento}")
    public ResponseEntity<?> updateEventoAsitentes(@Valid @RequestBody EventoDTO eventoDTO, @PathVariable(value = "idEvento")Integer idEvento){
        try {
            Evento eventoRespose= eventoService.updateAsistentes(eventoDTO,idEvento);
            return ResponseEntity
                    .ok()
                    .body(eventoRespose);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No se encontro el evento con id "+idEvento+"no existe, para actualizar"));
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @DeleteMapping("/delete/{idEvento}")
    public ResponseEntity<?> deleteEvento(@PathVariable(value = "idEvento")Integer idEvento){
        try{
            eventoService.deleteEvento(idEvento);
            return ResponseEntity.ok()
                    .body(new MessageResponse("Correcto: EL evento se elimino"));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No se encontro: El evento seleccionado"));
        }
    }




}
