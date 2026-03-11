package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.motorista.motorista_api.model.Parada;
import com.motorista.motorista_api.service.ParadaService;

@RestController
@RequestMapping("/paradas")
public class ParadaController {

    private final ParadaService paradaService;

    public ParadaController(ParadaService paradaService) {
        this.paradaService = paradaService;
    }

    // Criar parada
    @PostMapping
    public ResponseEntity<Parada> criar(@RequestBody Parada parada) {
        return ResponseEntity.ok(paradaService.salvar(parada));
    }

    // Listar paradas
    @GetMapping
    public ResponseEntity<List<Parada>> listar() {
        return ResponseEntity.ok(paradaService.listarTodas());
    }
}