package com.duoc.cloudlearningconsumer.service;

import com.duoc.cloudlearningconsumer.dto.*;
import com.duoc.cloudlearningconsumer.model.ResumenInscripcion;
import com.duoc.cloudlearningconsumer.repository.ResumenInscripcionRepository;
import com.duoc.cloudlearningconsumer.repository.S3Repository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Date;
import java.util.stream.Collectors;

@Service
public class RabbitMQConsumer {

    private static final String QUEUE = "myQueue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private ResumenInscripcionRepository repository;

    @Autowired
    private S3Repository s3Repository;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public void consumirMensaje() {

        InscripcionResumenDTO resumen =
                (InscripcionResumenDTO) rabbitTemplate.receiveAndConvert(QUEUE);

        if (resumen == null) {
            throw new RuntimeException("No existen mensajes en la cola.");
        }

        // Guardar en H2
        ResumenInscripcion entidad = new ResumenInscripcion();
        entidad.setEstudiante(resumen.getEstudiante());
        entidad.setTotal(resumen.getTotal());

        repository.save(entidad);

        // Subir a S3
        subirResumenAS3(resumen);

        System.out.println("Resumen guardado correctamente.");
    }

    public String subirResumenAS3(InscripcionResumenDTO resumen) {

        try {

            String nombreArchivo = "resumen_" + System.currentTimeMillis() + ".txt";

            File archivo = new File(nombreArchivo);

            try (FileWriter writer = new FileWriter(archivo)) {
                writer.write(generarContenidoResumen(resumen));
            }

            String rutaS3 = "resumenes/" + nombreArchivo;

            return s3Repository.uploadFile(
                    bucketName,
                    rutaS3,
                    archivo
            );

        } catch (IOException e) {
            throw new RuntimeException("Error al subir resumen a S3", e);
        }
    }

    private String generarContenidoResumen(InscripcionResumenDTO resumen) {

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
}
