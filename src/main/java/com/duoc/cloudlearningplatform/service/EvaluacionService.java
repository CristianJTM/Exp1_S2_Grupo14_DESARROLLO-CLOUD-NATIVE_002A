package com.duoc.cloudlearningplatform.service;


import com.duoc.cloudlearningplatform.dto.EvaluacionDTO;
import com.duoc.cloudlearningplatform.exception.ResourceNotFoundException;
import com.duoc.cloudlearningplatform.model.Curso;
import com.duoc.cloudlearningplatform.model.Evaluacion;
import com.duoc.cloudlearningplatform.repository.CursoRepository;
import com.duoc.cloudlearningplatform.repository.EvaluacionRepository;
import com.duoc.cloudlearningplatform.repository.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EvaluacionService {

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private EvaluacionRepository evaluacionRepository;

    public List<EvaluacionDTO> findAll(){

        return evaluacionRepository.findAll()
                .stream()
                .map(this::toDTO).toList();
    }

    public EvaluacionDTO findById(Long id){
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada"));
        return toDTO(evaluacion);
    }

    public List<EvaluacionDTO> findByCurso(Long cursoId){
        return evaluacionRepository.findByCursoId(cursoId)
                .stream().map(this::toDTO).toList();
    }

    public EvaluacionDTO saveEvaluacion(EvaluacionDTO evaluacionDTO){
        Curso curso = cursoRepository.findById(evaluacionDTO.getCursoId())
                .orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));

        Evaluacion evaluacion = new Evaluacion();
        evaluacion.setCurso(curso);
        evaluacion.setNombre(evaluacionDTO.getNombre());
        evaluacion.setPuntajeMaximo(evaluacionDTO.getPuntajeMaximo());
        evaluacion.setFechaAplicacion(evaluacionDTO.getFechaAplicacion());

        evaluacionRepository.save(evaluacion);
        return  toDTO(evaluacion);
    }

    public EvaluacionDTO updateEvaluacion(Long id,EvaluacionDTO evaluacionDTO){
        Evaluacion evaluacion = evaluacionRepository.findById(id).orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada"));

        evaluacion.setNombre(evaluacionDTO.getNombre());
        evaluacion.setPuntajeMaximo(evaluacionDTO.getPuntajeMaximo());
        evaluacion.setFechaAplicacion(evaluacionDTO.getFechaAplicacion());

        Long cursoId = evaluacionDTO.getCursoId();
        Curso curso = cursoRepository.findById(cursoId).orElseThrow(() -> new ResourceNotFoundException("Curso no encontrado"));
        evaluacion.setCurso(curso);

        evaluacionRepository.save(evaluacion);

        return   toDTO(evaluacion);
    }

    public void deleteEvaluacion(Long id){
        Evaluacion evaluacion = evaluacionRepository.findById(id)
                .orElseThrow(() -> new ResourceNotFoundException("Evaluacion no encontrada"));
        evaluacionRepository.delete(evaluacion);
    }

    private EvaluacionDTO toDTO(Evaluacion evaluacion) {

        EvaluacionDTO dto = new EvaluacionDTO();

        dto.setNombre(evaluacion.getNombre());
        dto.setCursoId(evaluacion.getCurso().getId());
        dto.setFechaAplicacion(evaluacion.getFechaAplicacion());
        dto.setPuntajeMaximo(evaluacion.getPuntajeMaximo());

        return dto;
    }
}
