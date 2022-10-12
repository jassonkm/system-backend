package com.system.permission.systembackend.web.controllers;


import com.system.permission.systembackend.domain.model.ERoles;
import com.system.permission.systembackend.domain.model.Rol;
import com.system.permission.systembackend.domain.model.Usuario;
import com.system.permission.systembackend.repository.RoleRepository;
import com.system.permission.systembackend.repository.UsuarioRepository;
import com.system.permission.systembackend.services.impl.UserDetailsImpl;
import com.system.permission.systembackend.web.security.jwt.JwtUtils;
import com.system.permission.systembackend.web.payload.request.LoginRequest;
import com.system.permission.systembackend.web.payload.request.SignupRequest;
import com.system.permission.systembackend.web.payload.response.JwtResponse;
import com.system.permission.systembackend.web.payload.response.MessageResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.io.IOException;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@CrossOrigin(origins = "*",maxAge = 3600)
@RestController
@RequestMapping("/auth")
public class AuthController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UsuarioRepository usuarioRepository;

    @Autowired
    RoleRepository roleRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;

    @PostMapping("/login")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getEmail(), loginRequest.getPassword()));
        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        List<String> roles = userDetails.getAuthorities().stream()
                .map(GrantedAuthority::getAuthority)
                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(
                userDetails.getId(),
                userDetails.getEmail(),
                jwt,
                roles));
    }
    @PostMapping("/register")
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest) throws IOException {
        if (usuarioRepository.existsByEmail(signUpRequest.getEmail())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡El nombre de usuario ya está en uso!"));
        }
        if (usuarioRepository.existsByIdNumero(signUpRequest.getIdNum())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: ¡El correo electrónico ya está en uso!"));
        }
        // Crear nueva cuenta de usuario
        Usuario user = new Usuario(
                signUpRequest.getNombre(),
                signUpRequest.getApellido(),
                signUpRequest.getEmail(),
                encoder.encode(signUpRequest.getPassword()),
                signUpRequest.getTypeid(),
                signUpRequest.getIdNum(),
                signUpRequest.getBirthday());

//        //TODO; VERIFICAR SI AUN SE SIGUEN ENVIANDO LOS CORREOS DE BIENVENIDA (ME CANCELARON LA CUENTA)
//        //enviar mensaje de bienvenida
//        mailService.sendTextEmail(signUpRequest.getEmail());

        Set<String> strRoles = signUpRequest.getRole();
        Set<Rol> roles = new HashSet<>();
        if (strRoles == null) {
            Rol userRole = roleRepository.findByName(ERoles.ROL_USER)
                    .orElseThrow(() -> new RuntimeException("Error: No se encuentra el rol."));
            roles.add(userRole);
        } else {
            strRoles.forEach(role -> {
                switch (role) {
                    case "admin":
                        Rol adminRole = roleRepository.findByName(ERoles.ROLE_ADMIN)
                                .orElseThrow(() -> new RuntimeException("Error: No se encuentra el rol ADMIN."));
                        roles.add(adminRole);
                        break;
                    default:
                        Rol userRole = roleRepository.findByName(ERoles.ROL_USER)
                                .orElseThrow(() -> new RuntimeException("Error: No se encuentra el rol USER."));
                        roles.add(userRole);
                }
            });
        }
        user.setRoles(roles);
        usuarioRepository.save(user);
        return ResponseEntity.ok(new MessageResponse("¡Usuario registrado con éxito!"));
    }
}
