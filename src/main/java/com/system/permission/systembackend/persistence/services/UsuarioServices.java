package com.system.permission.systembackend.persistence.services;

import com.system.permission.systembackend.persistence.model.Usuario;
import com.system.permission.systembackend.persistence.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class UsuarioServices {
    @Autowired
    UsuarioRepository usuarioRepository;

    public Optional<Usuario> getUsuario(String email){
        return usuarioRepository.findByEmail(email);
    }
    public Usuario saveUsuario(Usuario usuario){
        return  usuarioRepository.save(usuario);
    }
}
