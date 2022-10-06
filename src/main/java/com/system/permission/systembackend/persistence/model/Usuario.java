package com.system.permission.systembackend.persistence.model;

import com.fasterxml.jackson.annotation.JsonIgnore;


import javax.persistence.*;

import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;
    private String nombre;

    private String apellido;
    private String email;
    private String password;
    @Column(name = "type_documento")
    private String typeid;
    @Column(name = "id_numero")
    private String idNumero;

    private LocalDate birthday;
    private boolean estado=true;

    @OneToMany(cascade = CascadeType.ALL, fetch = FetchType.EAGER,mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioRol> usuarioRols = new HashSet<>();

    @OneToMany(cascade = CascadeType.ALL,fetch = FetchType.EAGER,mappedBy = "usuario")
    @JsonIgnore
    private Set<UsuarioEvento> usuarioEventos= new HashSet<>();

    public Usuario() {
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getType_id() {
        return typeid;
    }

    public void setType_id(String type_id) {
        this.typeid = type_id;
    }

    public String getIdNumero() {
        return idNumero;
    }

    public void setIdNumero(String idNumero) {
        this.idNumero = idNumero;
    }

    public LocalDate getBirthday() {
        return birthday;
    }

    public void setBirthday(LocalDate birthday) {
        this.birthday = birthday;
    }

    public boolean isEstado() {
        return estado;
    }

    public void setEstado(boolean estado) {
        this.estado = estado;
    }

    public Set<UsuarioRol> getUsuarioRols() {
        return usuarioRols;
    }

    public void setUsuarioRols(Set<UsuarioRol> usuarioRols) {
        this.usuarioRols = usuarioRols;
    }

    public Set<UsuarioEvento> getUsuarioEventos() {
        return usuarioEventos;
    }

    public void setUsuarioEventos(Set<UsuarioEvento> usuarioEventos) {
        this.usuarioEventos = usuarioEventos;
    }
}
