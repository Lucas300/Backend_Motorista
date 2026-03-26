package com.motorista.motorista_api.dto;

public class LinhaKmDTO {

    private Double kmTotal;
    private Double kmPlanejado;
    private Boolean excedeu;

    public LinhaKmDTO(Double kmTotal, Double kmPlanejado, Boolean excedeu) {
    	this.kmTotal = kmTotal;
        this.kmPlanejado = kmPlanejado;
        this.excedeu = excedeu;
    }

    public Double getKmTotal() {
        return kmTotal;
    }

    public Double getKmPlanejado() {
        return kmPlanejado;
    }

    public Boolean getExcedeu() {
        return excedeu;
    }
}