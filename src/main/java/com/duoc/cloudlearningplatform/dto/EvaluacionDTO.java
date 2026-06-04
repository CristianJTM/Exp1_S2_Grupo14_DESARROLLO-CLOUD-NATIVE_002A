package com.duoc.cloudlearningplatform.dto;

import java.util.Date;

public class EvaluacionDTO {
    private Long cursoId;
    private String nombre;
    private int puntajeMaximo;
    private Date fechaAplicacion;

    public EvaluacionDTO() {}

    public EvaluacionDTO(Long cursoId,  String nombre, int puntajeMaximo, Date fechaAplicacion) {
        this.cursoId = cursoId;
        this.nombre = nombre;
        this.puntajeMaximo = puntajeMaximo;
        this.fechaAplicacion = fechaAplicacion;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public int getPuntajeMaximo() {
        return puntajeMaximo;
    }

    public void setPuntajeMaximo(int puntajeMaximo) {
        this.puntajeMaximo = puntajeMaximo;
    }

    public Date getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(Date fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }
}
