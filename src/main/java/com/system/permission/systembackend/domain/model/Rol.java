package com.system.permission.systembackend.domain.model;

import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "rol")
public class Rol implements Serializable {
    private static final long serialVersionUID=11;

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Enumerated(EnumType.STRING)
    @Column(length = 20)
    private ERoles name;

    public Rol(){}

    public Rol(ERoles name) {
        this.name = name;
    }
}
