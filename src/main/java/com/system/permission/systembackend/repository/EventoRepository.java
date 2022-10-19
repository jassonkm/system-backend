package com.system.permission.systembackend.repository;

import com.system.permission.systembackend.domain.model.Evento;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface EventoRepository extends JpaRepository<Evento,Integer> {

    List<Evento> findByNombre(String nombre);
    Boolean existsBynombre(String nombre);

    List<Evento> findBynAsistentes(String asistente);



}
