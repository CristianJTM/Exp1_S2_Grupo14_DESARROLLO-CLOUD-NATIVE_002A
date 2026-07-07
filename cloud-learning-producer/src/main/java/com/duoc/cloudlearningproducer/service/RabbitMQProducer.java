package com.duoc.cloudlearningproducer.service;

import com.duoc.cloudlearningproducer.dto.InscripcionResumenDTO;
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

    public void enviarResumen(Long estudianteId) {

        InscripcionResumenDTO resumen =
                inscripcionService.findByEstudiante(estudianteId);

        rabbitTemplate.convertAndSend(QUEUE, resumen);

        System.out.println("Resumen enviado a RabbitMQ correctamente.");
    }
}
