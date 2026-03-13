package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.motorista.motorista_api.model.ParadaReal;
import com.motorista.motorista_api.service.ParadaRealService;

@RestController
@RequestMapping("/paradas-reais")
public class ParadaRealController {

    private final ParadaRealService paradaRealService;

    public ParadaRealController(ParadaRealService paradaRealService) {
        this.paradaRealService = paradaRealService;
    }

    @PostMapping("/{viagemId}")
    public ResponseEntity<ParadaReal> registrar(
            @PathVariable Long viagemId,
            @RequestBody ParadaReal parada) {

        return ResponseEntity.ok(paradaRealService.registrar(viagemId, parada));
    }

    @GetMapping("/{viagemId}")
    public ResponseEntity<List<ParadaReal>> listar(
            @PathVariable Long viagemId) {

        return ResponseEntity.ok(paradaRealService.listar(viagemId));
    }
}