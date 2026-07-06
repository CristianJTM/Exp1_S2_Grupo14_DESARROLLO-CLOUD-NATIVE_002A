package com.duoc.cloudlearningplatform.controller;


import com.duoc.cloudlearningplatform.dto.InscripcionDTO;
import com.duoc.cloudlearningplatform.dto.InscripcionResumenDTO;
import com.duoc.cloudlearningplatform.service.InscripcionService;
import com.duoc.cloudlearningplatform.service.RabbitMQProducer;
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

    @Autowired
    private RabbitMQProducer rabbitMQProducer;

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
    @PostMapping("/estudiante/{id}/resumen/s3")
    public ResponseEntity<String> subirResumenAS3(@PathVariable Long id) {
        return ResponseEntity.ok(
                inscripcionService.subirResumenAS3(id)
        );
    }

    //Post • Subir informe por estudiante
    @PostMapping("/estudiante/{id}/resumen/mq")
    public ResponseEntity<String> subirResumenMq(@PathVariable Long id) {
        rabbitMQProducer.enviarResumen(id);

        return ResponseEntity.ok("Resumen enviado a RabbitMQ");
    }

}
