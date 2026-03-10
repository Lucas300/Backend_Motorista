package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.service.ViagemService;

@RestController
@RequestMapping("/viagens")
public class ViagemController {

    private final ViagemService service;

    public ViagemController(ViagemService service) {
        this.service = service;
    }

    @PostMapping
    public Viagem criar(@RequestBody Viagem viagem){
        return service.salvar(viagem);
    }

    @GetMapping
    public List<Viagem> listar(){
        return service.listar();
    }
}
