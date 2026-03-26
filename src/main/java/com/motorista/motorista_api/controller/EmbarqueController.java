package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motorista.motorista_api.dto.EmbarqueDTO;
import com.motorista.motorista_api.model.Embarque;
import com.motorista.motorista_api.service.EmbarqueService;

@RestController
@RequestMapping("/embarques")
public class EmbarqueController {

    private final EmbarqueService embarqueService;

    public EmbarqueController(EmbarqueService embarqueService) {
        this.embarqueService = embarqueService;
    }

    @PostMapping
    public ResponseEntity<Embarque> registrar(@RequestBody EmbarqueDTO dto) {
        return ResponseEntity.ok(embarqueService.registrarEmbarque(dto));
    }
    
    @GetMapping("/viagem/{viagemId}")
    public ResponseEntity<List<Embarque>> listarPorViagem(@PathVariable Long viagemId) {

        return ResponseEntity.ok(embarqueService.listarPorViagem(viagemId));
    }
}