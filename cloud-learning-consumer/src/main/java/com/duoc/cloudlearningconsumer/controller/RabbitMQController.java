package com.duoc.cloudlearningconsumer.controller;

import com.duoc.cloudlearningconsumer.service.RabbitMQConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/consumer")
public class RabbitMQController {

    @Autowired
    private RabbitMQConsumer rabbitMQConsumer;

    @PostMapping("/procesar")
    public ResponseEntity<String> procesarMensaje() {

        rabbitMQConsumer.consumirMensaje();

        return ResponseEntity.ok("Resumen procesado correctamente.");
    }
}
