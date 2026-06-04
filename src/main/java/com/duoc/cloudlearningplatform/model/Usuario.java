package com.duoc.cloudlearningplatform.model;


import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.List;

@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotBlank(message = "El nombre es obligatorio")
    private String nombre;

    @NotBlank(message = "El correo es obligatorio")
    private String correo;

    @JsonIgnore
    @NotBlank(message = "La contraseña es obligatoria")
    private String contrasenia;

    @NotBlank(message = "El rol es obligatorio")
    private String rol;

    @JsonIgnore
    @OneToMany(mappedBy = "profesor")
    private List<Curso> cursos;

    @JsonIgnore
    @OneToMany(mappedBy = "estudiante")
    private List<Inscripcion> inscripciones;

    public Usuario() {

    }

    public Usuario(String nombre, String correo, String contrasenia, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContrasenia(){
        return contrasenia;
    }

    public void setContrasenia(String contrasenia) {
        this.contrasenia = contrasenia;
    }

    public String getRol() {
        return rol;
    }

    public void setRol(String rol) {
        this.rol = rol;
    }
}
