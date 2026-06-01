package com.duoc.cloudLearningPlatform.controller;


import com.duoc.cloudLearningPlatform.dto.InscripcionDTO;
import com.duoc.cloudLearningPlatform.dto.InscripcionResumenDTO;
import com.duoc.cloudLearningPlatform.model.Inscripcion;
import com.duoc.cloudLearningPlatform.service.InscripcionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.core.io.Resource;

import java.util.List;

@RestController
@RequestMapping("/api/inscripciones")
public class InscripcionController {

    @Autowired
    private InscripcionService inscripcionService;

    //GET •	Consultar inscripciones por curso
    @GetMapping("/curso/{id}")
    public ResponseEntity<List<InscripcionDTO>> findByCurso(@PathVariable Long id){
        return ResponseEntity.ok(inscripcionService.findByCurso(id));
    }

    //GET •	Consultar inscripciones por estudiante
    @GetMapping("/estudiante/{id}")
    public ResponseEntity<InscripcionResumenDTO> findByEstudiante(@PathVariable Long id) {
        return ResponseEntity.ok(inscripcionService.findByEstudiante(id));
    }

    //POST • Registrar inscripción
    @PostMapping
    public ResponseEntity<InscripcionDTO> saveInscripcion(@RequestBody InscripcionDTO dto) {
        return ResponseEntity.status(201).body(inscripcionService.saveInscripcion(dto));
    }

    //DELETE • Eliminar inscripción
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteInscripcion(@PathVariable Long id){
        inscripcionService.deleteInscripcion(id);
        return ResponseEntity.noContent().build();
    }

    //GET • Generar informe por estudiante
    @GetMapping("/estudiante/{id}/resumen")
    public ResponseEntity<Resource> descargarResumen(@PathVariable Long id) {
        return inscripcionService.generarResumenArchivo(id);
    }

    //Post • Subir informe por estudiante
    @PostMapping("/estudiante/{id}/subir-resumen")
    public ResponseEntity<String> subirResumenAS3(@PathVariable Long id) {
        return ResponseEntity.ok(
                inscripcionService.subirResumenAS3(id)
        );
    }
}
