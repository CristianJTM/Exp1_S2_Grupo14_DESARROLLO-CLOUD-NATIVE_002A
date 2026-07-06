package com.duoc.cloudlearningplatform.service;

import com.duoc.cloudlearningplatform.dto.CursoResumenDTO;
import com.duoc.cloudlearningplatform.dto.InscripcionResumenDTO;
import com.duoc.cloudlearningplatform.model.ResumenInscripcion;
import com.duoc.cloudlearningplatform.repository.ResumenInscripcionRepository;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.stream.Collectors;

@Service
public class RabbitMQConsumer {
    @Autowired
    private ResumenInscripcionRepository repository;

    @RabbitListener(queues = "myQueue")
    public void consumirResumen(InscripcionResumenDTO dto) {

        ResumenInscripcion resumen = new ResumenInscripcion();

        resumen.setEstudiante(dto.getEstudiante());

        resumen.setTotal(dto.getTotal());

        resumen.setFechaProcesamiento(new Date());

        String cursos = dto.getCursos()
                .stream()
                .map(CursoResumenDTO::getNombre)
                .collect(Collectors.joining(", "));

        resumen.setCursos(cursos);

        repository.save(resumen);

        System.out.println("Resumen guardado correctamente en la base de datos.");

    }
}
