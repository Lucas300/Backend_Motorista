package com.motorista.motorista_api.controller;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.motorista.motorista_api.model.Veiculo;
import com.motorista.motorista_api.service.VeiculoService;

@RestController
@RequestMapping("/veiculos")
public class VeiculoController {

    private final VeiculoService service;

    public VeiculoController(VeiculoService service) {
        this.service = service;
    }

    @PostMapping
    public Veiculo criar(@RequestBody Veiculo veiculo){
        return service.salvar(veiculo);
    }

    @GetMapping
    public List<Veiculo> listar(){
        return service.listar();
    }
}
