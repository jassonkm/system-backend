package com.system.permission.systembackend.repository;

import com.system.permission.systembackend.domain.model.Locaciones;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocacionesRepository extends JpaRepository<Locaciones,Integer> {
    Boolean existsByNombre(String nombre);
    List<Locaciones> findByNombre(String nombre);
}
