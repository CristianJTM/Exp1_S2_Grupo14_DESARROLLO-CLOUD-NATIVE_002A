package com.duoc.cloudlearningconsumer.controller;

import com.duoc.cloudlearningconsumer.model.ResumenInscripcion;
import com.duoc.cloudlearningconsumer.repository.ResumenInscripcionRepository;
import com.duoc.cloudlearningconsumer.service.RabbitMQConsumer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/consumer")
public class RabbitMQController {

    @Autowired
    private RabbitMQConsumer rabbitMQConsumer;

    @Autowired
    private ResumenInscripcionRepository resumenInscripcionRepository;

    @PostMapping("/procesar")
    public ResponseEntity<String> procesarMensaje() {

        rabbitMQConsumer.consumirMensaje();

        return ResponseEntity.ok("Resumen procesado correctamente.");
    }

    @GetMapping("/resumenes")
    public List<ResumenInscripcion> listar() {
        return resumenInscripcionRepository.findAll();
    }
}
