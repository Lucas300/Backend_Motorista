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

import com.motorista.motorista_api.dto.IniciarViagemDTO;
import com.motorista.motorista_api.dto.ViagemKmDTO;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.service.ViagemService;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @PostMapping
    public Viagem criar(@RequestBody Viagem viagem){
        return viagemService.salvar(viagem);
    }

    @GetMapping
    public List<Viagem> listar(){
        return viagemService.listar();
    }
    
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deletar(@PathVariable Long id) {
        viagemService.deletar(id);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Viagem> iniciar(@RequestBody IniciarViagemDTO dto) {

        Viagem viagem = viagemService.iniciarViagem(
                dto.getMotoristaId(),
                dto.getVeiculoId(),
                dto.getLinhaId());

        return ResponseEntity.ok(viagem);
    }

    @PutMapping("/finalizar/{id}")
    public Viagem finalizarViagem(@PathVariable("id") Long id) {
        return viagemService.finalizarViagem(id);
    }
    
    @GetMapping("/{id}/km")
    public ResponseEntity<ViagemKmDTO> obterKmViagem(@PathVariable Long id) {
        return ResponseEntity.ok(viagemService.obterResumoKmViagem(id));
    }
}