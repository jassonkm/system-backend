package com.system.permission.systembackend.persistence.repository;

import com.system.permission.systembackend.persistence.model.Usuario;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.Optional;

@Repository
public interface UsuarioRepository extends CrudRepository<Usuario,Integer> {
    public abstract Optional<Usuario> findByEmail(String email);


}
