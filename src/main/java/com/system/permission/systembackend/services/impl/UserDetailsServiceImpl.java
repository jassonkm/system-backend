package com.system.permission.systembackend.services.impl;

import com.system.permission.systembackend.domain.model.Usuario;
import com.system.permission.systembackend.repository.UsuarioRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;


@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    private static final Logger logger =LoggerFactory.getLogger(UserDetailsServiceImpl.class);

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Transactional
    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        Usuario user = usuarioRepository.findByEmail(email)
                .orElseThrow(()-> new UsernameNotFoundException("No se encontro el correo del usuario: "+email));
        return UserDetailsImpl.build(user);
    }
}
