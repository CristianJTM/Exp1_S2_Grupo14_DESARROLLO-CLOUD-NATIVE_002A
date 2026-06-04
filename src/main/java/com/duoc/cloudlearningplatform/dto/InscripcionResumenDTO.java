package com.duoc.cloudlearningplatform.dto;

import java.util.List;

public class InscripcionResumenDTO {
    private String estudiante;
    private List<CursoResumenDTO> cursos;
    private Double total;

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
