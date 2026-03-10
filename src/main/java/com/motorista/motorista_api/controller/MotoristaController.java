package com.motorista.motorista_api.controller;

import java.util.List;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motorista.motorista_api.model.Motorista;
import com.motorista.motorista_api.service.MotoristaService;

@RestController
@RequestMapping("/motoristas")
public class MotoristaController {

    private final MotoristaService service;

    public MotoristaController(MotoristaService service) {
        this.service = service;
    }

    @PostMapping
    public Motorista criar(@RequestBody Motorista motorista){
        return service.salvar(motorista);
    }

    @GetMapping
    public List<Motorista> listar(){
        return service.listar();
    }

}
