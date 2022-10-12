package com.system.permission.systembackend.repository;

import com.system.permission.systembackend.domain.model.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UsuarioRepository extends JpaRepository<Usuario,Long> {
    Optional<Usuario> findByEmail(String email);
    Boolean existsByIdNumero(String idNumero);
    Boolean existsByEmail(String email);
}
