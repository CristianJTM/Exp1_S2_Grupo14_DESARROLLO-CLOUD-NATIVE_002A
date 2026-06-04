package com.duoc.cloudlearningplatform.dto;

public class UsuarioDTO {
    private String nombre;
    private String correo;
    private String contrasenia;
    private String rol;

    public UsuarioDTO() {}

    public UsuarioDTO(String nombre, String correo, String contrasenia, String rol) {
        this.nombre = nombre;
        this.correo = correo;
        this.contrasenia = contrasenia;
        this.rol = rol;
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

    public String getContrasenia() {
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
