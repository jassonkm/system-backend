package com.system.permission.systembackend.repository;

import com.system.permission.systembackend.domain.model.ERoles;
import com.system.permission.systembackend.domain.model.Rol;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RoleRepository extends JpaRepository<Rol, Long> {

    Optional<Rol> findByName(ERoles name);
}
