package com.duoc.cloudlearningproducer.repository;

import com.duoc.cloudlearningproducer.model.Inscripcion;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface InscripcionRepository extends JpaRepository<Inscripcion, Long> {

    List<Inscripcion> findByCursoId(Long cursoId);

    List<Inscripcion> findByEstudianteId(Long estudianteId);
}
