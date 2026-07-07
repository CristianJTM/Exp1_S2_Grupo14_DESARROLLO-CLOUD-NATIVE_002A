package com.duoc.cloudlearningproducer.dto;

import java.util.Date;

public class InscripcionDTO {
    private Long cursoId;
    private Long estudianteId;
    private Date fechaInscripcion;

    public InscripcionDTO() {}

    public InscripcionDTO(Long cursoId, Long estudianteId,  Date fechaInscripcion) {
        this.cursoId = cursoId;
        this.estudianteId = estudianteId;
        this.fechaInscripcion = fechaInscripcion;
    }

    public Long getCursoId() {
        return cursoId;
    }

    public void setCursoId(Long cursoId) {
        this.cursoId = cursoId;
    }

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public Date getFechaInscripcion() {
        return fechaInscripcion;
    }

    public void setFechaInscripcion(Date fechaInscripcion) {
        this.fechaInscripcion = fechaInscripcion;
    }

}
