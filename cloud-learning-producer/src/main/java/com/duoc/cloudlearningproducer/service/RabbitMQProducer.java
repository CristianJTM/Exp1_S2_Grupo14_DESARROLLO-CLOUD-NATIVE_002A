package com.duoc.cloudlearningproducer.service;

import com.duoc.cloudlearningproducer.dto.InscripcionResumenDTO;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RabbitMQProducer {
    private static final String QUEUE = "myQueue";

    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private InscripcionService inscripcionService;

    @Autowired
    private ObjectMapper objectMapper;

    public void enviarResumen(Long estudianteId) {

        try {

            InscripcionResumenDTO resumen =
                    inscripcionService.findByEstudiante(estudianteId);

            String json = objectMapper.writeValueAsString(resumen);

            rabbitTemplate.convertAndSend(QUEUE, json);

            System.out.println("Resumen enviado correctamente.");

        } catch (Exception e) {
            throw new RuntimeException("Error enviando mensaje a RabbitMQ", e);
        }
    }
}
