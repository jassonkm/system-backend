package com.system.permission.systembackend.persistence.model;


import javax.persistence.*;


@Entity
@Table(name = "Usuario_evento")
public class UsuarioEvento {
    @Id
    @Column(name = "id_UsuarioEvento")
    private Integer idUsuarioEvento;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_usuario")
    private Usuario usuario;
    @ManyToOne
    @JoinColumn(name = "id_evento")
    private Evento evento;

    public UsuarioEvento(){
    }

    public Integer getIdUsuarioEvento() {
        return idUsuarioEvento;
    }

    public void setIdUsuarioEvento(Integer idUsuarioEvento) {
        this.idUsuarioEvento = idUsuarioEvento;
    }

    public Usuario getUsuario() {
        return usuario;
    }

    public void setUsuario(Usuario usuario) {
        this.usuario = usuario;
    }

    public Evento getEvento() {
        return evento;
    }

    public void setEvento(Evento evento) {
        this.evento = evento;
    }
}
