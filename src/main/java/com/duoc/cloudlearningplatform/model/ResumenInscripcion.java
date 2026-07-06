package com.duoc.cloudlearningplatform.model;

import jakarta.persistence.*;

import java.util.Date;

@Entity
public class ResumenInscripcion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String estudiante;

    @Lob
    private String cursos;

    private Double total;

    private Date fechaProcesamiento;

    // getters y setters

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public String getCursos() {
        return cursos;
    }

    public void setCursos(String cursos) {
        this.cursos = cursos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }

    public Date getFechaProcesamiento() {
        return fechaProcesamiento;
    }

    public void setFechaProcesamiento(Date fechaProcesamiento) {
        this.fechaProcesamiento = fechaProcesamiento;
    }
}