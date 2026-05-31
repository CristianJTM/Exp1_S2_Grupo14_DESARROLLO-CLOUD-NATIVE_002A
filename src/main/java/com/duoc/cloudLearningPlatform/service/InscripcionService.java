package com.duoc.cloudLearningPlatform.service;


import com.duoc.cloudLearningPlatform.dto.CursoResumenDTO;
import com.duoc.cloudLearningPlatform.dto.EvaluacionDTO;
import com.duoc.cloudLearningPlatform.dto.InscripcionDTO;
import com.duoc.cloudLearningPlatform.dto.InscripcionResumenDTO;
import com.duoc.cloudLearningPlatform.exception.ResourceNotFoundException;
import com.duoc.cloudLearningPlatform.model.Curso;
import com.duoc.cloudLearningPlatform.model.Evaluacion;
import com.duoc.cloudLearningPlatform.model.Inscripcion;
import com.duoc.cloudLearningPlatform.model.Usuario;
import com.duoc.cloudLearningPlatform.repository.CursoRepository;
import com.duoc.cloudLearningPlatform.repository.InscripcionRepository;
import com.duoc.cloudLearningPlatform.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    public List<InscripcionDTO> findByCurso(Long cursoId){
        return inscripcionRepository.findByCursoId(cursoId)
                .stream().map(this::toDTO).toList();
    }

    public InscripcionResumenDTO findByEstudiante(Long estudianteId){
        List<Inscripcion> inscripciones = inscripcionRepository.findByEstudianteId(estudianteId);

        InscripcionResumenDTO inscripcionResumenDTO = new InscripcionResumenDTO();

        if(inscripciones.isEmpty()){
            throw new ResourceNotFoundException("Inscripcion no encontrada");
        }

        inscripcionResumenDTO.setEstudiante(inscripciones.get(0).getEstudiante().getNombre());

        List<CursoResumenDTO> cursos = inscripciones.stream().map(inscripcion -> {
            CursoResumenDTO cursoResumenDTO = new CursoResumenDTO();
            cursoResumenDTO.setNombre(inscripcion.getCurso().getNombre());
            cursoResumenDTO.setCosto(inscripcion.getCurso().getCosto());
            return cursoResumenDTO;
        }).toList();

        inscripcionResumenDTO.setCursos(cursos);

        inscripcionResumenDTO.setTotal(cursos.stream().mapToDouble(CursoResumenDTO::getCosto).sum());

        return inscripcionResumenDTO;
    }

    public InscripcionDTO saveInscripcion(InscripcionDTO inscripcionDTO){
        Curso curso = cursoRepository.findById(inscripcionDTO.getCursoId()).
                orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        Usuario estudiante = usuarioRepository.findById(inscripcionDTO.getEstudianteId()).
                orElseThrow(() -> new ResourceNotFoundException("Estudiante no encontrado"));

        Inscripcion inscripcion = new Inscripcion();

        inscripcion.setCurso(curso);
        inscripcion.setEstudiante(estudiante);
        inscripcion.setFechaInscripcion(inscripcionDTO.getFechaInscripcion());

        inscripcionRepository.save(inscripcion);
        return  toDTO(inscripcion);
    }

    public void deleteInscripcion(Long id){
        Inscripcion inscripcion = inscripcionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Inscripcion no encontrada"));
        inscripcionRepository.delete(inscripcion);
    }

    private InscripcionDTO toDTO(Inscripcion inscripcion) {

        InscripcionDTO dto = new InscripcionDTO();

        dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
        dto.setEstudianteId(inscripcion.getEstudiante().getId());
        dto.setCursoId(inscripcion.getCurso().getId());

        return dto;
    }
}
