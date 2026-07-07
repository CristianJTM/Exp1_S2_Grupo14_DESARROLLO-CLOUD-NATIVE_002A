package com.duoc.cloudlearningproducer.service;


import com.duoc.cloudlearningproducer.dto.CursoResumenDTO;
import com.duoc.cloudlearningproducer.dto.InscripcionDTO;
import com.duoc.cloudlearningproducer.dto.InscripcionResumenDTO;
import com.duoc.cloudlearningproducer.exception.ResourceNotFoundException;
import com.duoc.cloudlearningproducer.model.Curso;
import com.duoc.cloudlearningproducer.model.Inscripcion;
import com.duoc.cloudlearningproducer.model.Usuario;
import com.duoc.cloudlearningproducer.repository.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import org.springframework.core.io.ByteArrayResource;
import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.charset.StandardCharsets;


import java.util.List;

@Service
public class InscripcionService {

    @Autowired
    private InscripcionRepository inscripcionRepository;

    @Autowired
    private CursoRepository cursoRepository;

    @Autowired
    private UsuarioRepository usuarioRepository;

    @Autowired
    private S3Repository s3Repository;

    @Value("${aws.bucket.name}")
    private String bucketName;

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

    private String generarContenidoResumen(Long id) {

        InscripcionResumenDTO resumen = findByEstudiante(id);

        StringBuilder contenido = new StringBuilder();

        contenido.append("Resumen de Inscripción\n\n");
        contenido.append("Estudiante: ")
                .append(resumen.getEstudiante())
                .append("\n\n");

        contenido.append("Cursos:\n");

        for (CursoResumenDTO curso : resumen.getCursos()) {

            contenido.append("- ")
                    .append(curso.getNombre())
                    .append(" ($")
                    .append(curso.getCosto())
                    .append(")\n");
        }

        contenido.append("\nTotal: $")
                .append(resumen.getTotal());

        return contenido.toString();
    }

    public ResponseEntity<Resource> generarResumenArchivo(Long id) {

        String contenido = generarContenidoResumen(id);

        byte[] datos = contenido.getBytes(StandardCharsets.UTF_8);

        ByteArrayResource resource = new ByteArrayResource(datos);

        return ResponseEntity.ok()
                .header(
                        HttpHeaders.CONTENT_DISPOSITION,
                        "attachment; filename=resumen_" + id + ".txt"
                )
                .contentType(MediaType.TEXT_PLAIN)
                .contentLength(datos.length)
                .body(resource);
    }

    public String obtenerContenidoResumen(Long id) {
        return generarContenidoResumen(id);
    }

    public String subirResumenAS3(Long id) {

        String contenido = generarContenidoResumen(id);

        try {

            String nombreArchivo = "resumen_" + id + ".txt";

            File archivo = new File(nombreArchivo);

            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(contenido);
            }

            String rutaS3 = "resumenes/" + id + "/";

            return s3Repository.uploadFile(
                    bucketName,
                    rutaS3 + nombreArchivo,
                    archivo
            );

        } catch (IOException e) {
            throw new RuntimeException("Error al generar resumen", e);
        }
    }


    private InscripcionDTO toDTO(Inscripcion inscripcion) {

        InscripcionDTO dto = new InscripcionDTO();

        dto.setFechaInscripcion(inscripcion.getFechaInscripcion());
        dto.setEstudianteId(inscripcion.getEstudiante().getId());
        dto.setCursoId(inscripcion.getCurso().getId());

        return dto;
    }
}
