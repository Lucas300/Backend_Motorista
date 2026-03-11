package com.motorista.motorista_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.motorista.motorista_api.dto.IniciarViagemDTO;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.service.ViagemService;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    private final ViagemService viagemService;

    public ViagemController(ViagemService viagemService) {
        this.viagemService = viagemService;
    }

    @PostMapping("/iniciar")
    public ResponseEntity<Viagem> iniciar(@RequestBody IniciarViagemDTO dto) {

        Viagem viagem = viagemService.iniciarViagem(
                dto.getMotoristaId(),
                dto.getVeiculoId(),
                dto.getLinhaId());

        return ResponseEntity.ok(viagem);
    }
}
