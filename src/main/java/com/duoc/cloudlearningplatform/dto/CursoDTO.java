package com.duoc.cloudlearningplatform.dto;

public class CursoDTO {
    private String nombre;
    private String descripcion;
    private Long profesorId;
    private Integer duracion;
    private Double costo;

    public CursoDTO(){}

    public CursoDTO(String nombre, String descripcion, Long profesorId,  Integer duracion, Double costo) {
        this.nombre = nombre;
        this.descripcion = descripcion;
        this.profesorId = profesorId;
        this.duracion = duracion;
        this.costo = costo;
    }

    public String getNombre() {
        return nombre;
    }
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Long getProfesorId() {
        return profesorId;
    }

    public void setProfesorId(Long profesorId) {
        this.profesorId = profesorId;
    }

    public Integer getDuracion() {
        return duracion;
    }

    public void setDuracion(Integer duracion) {
        this.duracion = duracion;
    }

    public Double getCosto() {
        return costo;
    }

    public void setCosto(Double costo) {
        this.costo = costo;
    }
}
