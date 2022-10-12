package com.system.permission.systembackend.services.impl;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.system.permission.systembackend.domain.model.Usuario;
import lombok.Data;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.time.LocalDate;
import java.util.Collection;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@Data
public class UserDetailsImpl implements UserDetails {

    private static final long serialVersionUID=1L;

    private Long id;

    private String nombre;

    private String apellido;
    private String email;
    @JsonIgnore
    private String password;

    private String type_id;
    private String id_num;



    private LocalDate birthday;

    private Collection<? extends GrantedAuthority> authorities;

    public UserDetailsImpl(Long id, String nombre, String apellido, String email, String password, String type_id, String id_num, LocalDate birthday, Collection<? extends GrantedAuthority> authorities) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.email = email;
        this.password = password;
        this.type_id = type_id;
        this.id_num = id_num;
        this.birthday = birthday;
        this.authorities = authorities;
    }

    public static UserDetailsImpl build(Usuario usuario){
        List<GrantedAuthority> authorities =usuario.getRoles().stream()
                .map(rol -> new SimpleGrantedAuthority(rol.getName().name()))
                .collect(Collectors.toList());
        return new UserDetailsImpl(usuario.getId(),
                usuario.getNombre(),
                usuario.getApellido(),
                usuario.getEmail(),
                usuario.getPassword(),
                usuario.getTypeid(),
                usuario.getIdNumero(),
                usuario.getBirthday(),
                authorities);
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return email;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (o == null || getClass() != o.getClass())
            return false;
        UserDetailsImpl user = (UserDetailsImpl) o;
        return Objects.equals(id, user.id);
    }
}
