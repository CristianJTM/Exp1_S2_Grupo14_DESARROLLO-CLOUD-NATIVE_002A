package com.duoc.CloudLearningPlatform.controller;


import com.duoc.CloudLearningPlatform.dto.InscripcionDTO;
import com.duoc.CloudLearningPlatform.dto.InscripcionResumenDTO;
import com.duoc.CloudLearningPlatform.model.Inscripcion;
import com.duoc.CloudLearningPlatform.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    //GET •	Consultar inscripciones por curso
    @GetMapping("/curso/{id}")
    public ResponseEntity<List<Inscripcion>> findByCurso(@PathVariable Long id){
        return ResponseEntity.ok(inscripcionService.findByCurso(id));
    }

    //GET •	Consultar inscripciones por estudiante
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<InscripcionResumenDTO> findByEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.findByEstudiante(id));
    }

    //POST • Registrar inscripción
    @PostMapping
    public ResponseEntity<Inscripcion> saveInscripcion(@RequestBody InscripcionDTO dto) {
        return ResponseEntity.status(201).body(inscripcionService.saveInscripcion(dto));
    }

    //DELETE • Eliminar inscripción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id){
        inscripcionService.deleteInscripcion(id);
        return ResponseEntity.noContent().build();
    }
}
