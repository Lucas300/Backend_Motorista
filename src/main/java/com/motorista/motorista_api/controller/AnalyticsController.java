package com.motorista.motorista_api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.motorista.motorista_api.service.AnalyticsService;

@RestController
@RequestMapping("/analytics")
public class AnalyticsController {

    private final AnalyticsService analyticsService;

    public AnalyticsController(AnalyticsService analyticsService) {
        this.analyticsService = analyticsService;
    }

    @GetMapping("/ociosidade/{viagemId}")
    public ResponseEntity<Double> calcularOciosidade(
            @PathVariable Long viagemId) {

        double kmOcioso = analyticsService.calcularKmOcioso(viagemId);

        return ResponseEntity.ok(kmOcioso);
    }
}