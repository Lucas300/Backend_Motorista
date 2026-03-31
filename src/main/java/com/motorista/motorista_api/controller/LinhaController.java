package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motorista.motorista_api.dto.LinhaKmDTO;
import com.motorista.motorista_api.model.Linha;
import com.motorista.motorista_api.model.Parada;
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

    // Rota planejada
    @GetMapping("/{linhaId}/rota")
    public ResponseEntity<List<Parada>> rotaPlanejada(@PathVariable Long linhaId) {
        return ResponseEntity.ok(linhaService.buscarRotaPlanejada(linhaId));
    }
    
    @GetMapping("/{linhaId}/km-total")
    public ResponseEntity<LinhaKmDTO> obterKmTotal(@PathVariable Long linhaId) {
        return ResponseEntity.ok(linhaService.obterResumoKm(linhaId));
    }
    
 // Deletar linha
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        linhaService.deletar(id);
        return ResponseEntity.noContent().build();
    }
    
 // Atualizar linha
    @PutMapping("/{id}")
    public ResponseEntity<Linha> atualizar(@PathVariable Long id, @RequestBody Linha linha) {
        return ResponseEntity.ok(linhaService.atualizar(id, linha));
    }
}