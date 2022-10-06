package com.system.permission.systembackend.persistence.model;


import javax.persistence.*;

@Entity
public class UsuarioRol {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long userRolId;

    @ManyToOne(fetch = FetchType.EAGER)
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_rol")
    private Rol rol;

    public long getUserRolId() {
        return userRolId;
    }

    public void setUserRolId(long userRolId) {
        this.userRolId = userRolId;
    }

    public Usuario getUsers() {
        return usuario;
    }

    public void setUsers(Usuario usuario) {
        this.usuario = usuario;
    }

    public Rol getRol() {
        return rol;
    }

    public void setRol(Rol rol) {
        this.rol = rol;
    }
    public UsuarioRol(){
    }
}
