package com.motorista.motorista_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class ControllerSimples {

    @GetMapping("/teste")
    public String teste() {
        return "funcionando";
    }
}