package com.duoc.cloudlearningconsumer.dto;

import java.util.List;

public class InscripcionResumenDTO {
    private Long estudianteId;
    private String estudiante;
    private List<CursoResumenDTO> cursos;
    private Double total;

    public Long getEstudianteId() {
        return estudianteId;
    }

    public void setEstudianteId(Long estudianteId) {
        this.estudianteId = estudianteId;
    }

    public String getEstudiante() {
        return estudiante;
    }

    public void setEstudiante(String estudiante) {
        this.estudiante = estudiante;
    }

    public List<CursoResumenDTO> getCursos() {
        return cursos;
    }

    public void setCursos(List<CursoResumenDTO> cursos) {
        this.cursos = cursos;
    }

    public Double getTotal() {
        return total;
    }

    public void setTotal(Double total) {
        this.total = total;
    }
}
