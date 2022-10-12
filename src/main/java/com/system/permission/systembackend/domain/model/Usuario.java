package com.system.permission.systembackend.domain.model;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.time.LocalDate;
import java.util.HashSet;
import java.util.Set;

@Entity
@Data
@Table(name = "usuario",uniqueConstraints = {
        @UniqueConstraint(columnNames = "email"),
        @UniqueConstraint(columnNames = "id_numero")
})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private long id;

    @NotBlank
    private String nombre;

    @NotBlank
    private String apellido;


    @NotBlank
    @Size(max = 50)
    @Email
    private String email;

    @NotBlank
    @Size(max = 120)
    private String password;

    @Column(name = "type_documento")
    @NotBlank
    @Size(max = 20)
    private String typeid;

    @NotBlank
    @Size(max = 10)
    @Column(name = "id_numero")
    private String idNumero;


    private LocalDate birthday;

    private boolean estado=true;

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = @JoinColumn(name = "id_usuario"),
            inverseJoinColumns = @JoinColumn(name = "id_rol"))
    private Set<Rol> roles= new HashSet<>();

    public Usuario(){}

    public Usuario(String nombre, String apellido, String email, String password, String typeid, String idNumero, LocalDate birthday) {
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.typeid = typeid;
        this.idNumero = idNumero;
        this.birthday = birthday;
    }
}
