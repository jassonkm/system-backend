package com.system.permission.systembackend.persistence.controller;

import com.system.permission.systembackend.persistence.model.Usuario;
import com.system.permission.systembackend.persistence.services.UsuarioServices;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Optional;

@RestController
@RequestMapping("/usuario")
public class UsuarioController {
    @Autowired
    UsuarioServices usuarioServices;

    @PostMapping()
    public Usuario saveUsuario(@RequestBody Usuario usuario){
        return this.usuarioServices.saveUsuario(usuario);
    }

    @GetMapping(path = "/{email}")
    public Optional<Usuario> getEmail(@PathVariable("email") String email){
        return this.usuarioServices.getUsuario(email);
    }
}
