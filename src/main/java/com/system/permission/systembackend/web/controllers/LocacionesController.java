package com.system.permission.systembackend.web.controllers;

import com.system.permission.systembackend.domain.dto.LocacionesDTO;
import com.system.permission.systembackend.domain.model.Locaciones;
import com.system.permission.systembackend.services.LocacionesService;
import com.system.permission.systembackend.web.payload.response.LocacionesResponse;
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
@RequestMapping("/locaciones")
public class LocacionesController {

    @Autowired
    private LocacionesService locacionesService;

    @Autowired
    HttpServletRequest request;
    private static final Logger logger = LoggerFactory.getLogger(LocacionesController.class);

    @PreAuthorize("hasRole('ADMIN')")
    @PostMapping("/add")
    public ResponseEntity<?> addLocaciones(@Valid @RequestBody  Locaciones locaciones){
        if (locacionesService.isNombre(locaciones.getNombre())){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error el nombre ya está en uso"));
        }
        try {
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .body(locacionesService.addLocaciones(locaciones));
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error Locacion ya existe"));
        }
    }
    @GetMapping("/lista")
    public ResponseEntity<?>getListLocaciones(){
        try {
            return ResponseEntity
                    .ok()
                    .body(locacionesService.findLocaciones());
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("Ninguna locacion registrada"));
        }
    }
    @PreAuthorize("hasRole('ADMIN')")
    @PutMapping("/update/{idLocaciones}")
    public ResponseEntity<?> updateLocaciones(@Valid @RequestBody LocacionesDTO locacionesDTO,@PathVariable(value = "idLocaciones") Integer idLocaciones){
        try{
            Locaciones locacionesResponse=locacionesService.updateLocaciones(locacionesDTO,idLocaciones);
            return ResponseEntity
                    .ok()
                    .body(locacionesResponse);
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("La locacion con id "+idLocaciones+" no existe para ser actualizado"));
        }
    }
    @PreAuthorize("HasROle('ADMIN')")
    @DeleteMapping("/deleteLocation/{idLocaciones}")
    public ResponseEntity<?> deleteLocaciones (@PathVariable(value = "idLocaciones")Integer idLocaciones){
        try{
            locacionesService.deleteLocaciones(idLocaciones);
            return ResponseEntity
                    .ok()
                    .body(new MessageResponse("Correcto: La locacion fue eliminada"));
        }catch (Exception e){
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .body(new MessageResponse("No se encontro la  locacion"));
        }

    }
    @GetMapping("/locaciones")
    public ResponseEntity<?> getLocacionByNombre(String nombre){
        try{
            List<Locaciones>locacionesList=locacionesService.findLocacionesBynombre(nombre);
            List<LocacionesResponse>locacionesResponses=new ArrayList<>();
            for (Locaciones locaciones:locacionesList){
                LocacionesResponse locacionesResponse=new LocacionesResponse(locaciones.getNombre(),locaciones.getCapacidad());
                locacionesResponses.add(locacionesResponse);
            }
            if (!locacionesList.isEmpty()){
                return ResponseEntity
                        .ok()
                        .body(locacionesResponses);
            }else {
                return ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body(new MessageResponse("No se encontró la locaciones con nombre "+nombre));
            }
        }catch (Exception e){
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error el evento no existe"));
        }
    }

}
