package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.motorista.motorista_api.model.Linha;
import com.motorista.motorista_api.service.LinhaService;

@RestController
@RequestMapping("/linhas")
public class LinhaController {

    private final LinhaService linhaService;

    public LinhaController(LinhaService linhaService) {
        this.linhaService = linhaService;
    }

    // Listar todas as linhas
    @GetMapping
    public ResponseEntity<List<Linha>> listar() {
        return ResponseEntity.ok(linhaService.listarTodas());
    }

    // Buscar linha por ID
    @GetMapping("/{id}")
    public ResponseEntity<Linha> buscarPorId(@PathVariable Long id) {
        return ResponseEntity.ok(linhaService.buscarPorId(id));
    }

    // Criar nova linha
    @PostMapping
    public ResponseEntity<Linha> criar(@RequestBody Linha linha) {
        return ResponseEntity.ok(linhaService.salvar(linha));
    }
}