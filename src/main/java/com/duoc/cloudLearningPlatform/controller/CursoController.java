package com.duoc.cloudLearningPlatform.controller;


import com.duoc.cloudLearningPlatform.dto.CursoDTO;
import com.duoc.cloudLearningPlatform.model.Curso;
import com.duoc.cloudLearningPlatform.service.CursoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/cursos")
public class CursoController {

    @Autowired
    private CursoService cursoService;


    //GET •	Consultar todos los cursos
    @GetMapping
    public ResponseEntity<List<CursoDTO>> findAll(){
        return ResponseEntity.ok(cursoService.findAll());
    }

    //GET •	Consultar cuso por ID
    @GetMapping("/{id}")
    public ResponseEntity<CursoDTO> findById(@PathVariable Long id){
        return ResponseEntity.ok(cursoService.findById(id));
    }

    //POST • Registrar curso
    @PostMapping
    public ResponseEntity<CursoDTO> saveCurso(@RequestBody CursoDTO dto) {
        return ResponseEntity.status(201).body(cursoService.saveCurso(dto));
    }

    //PUT •	Modificar curso
    @PutMapping("/{id}")
    public ResponseEntity<CursoDTO> updateCurso(@PathVariable Long id, @RequestBody CursoDTO dto) {
        return ResponseEntity.ok(cursoService.updateCurso(id, dto));
    }

    //DELETE • Eliminar curso
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteCurso(@PathVariable Long id){
        cursoService.deleteCurso(id);
        return ResponseEntity.noContent().build();
    }


}
