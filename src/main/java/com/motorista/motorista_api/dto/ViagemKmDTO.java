package com.motorista.motorista_api.dto;

public class ViagemKmDTO {

    private Double kmReal;
    private Double kmPlanejado;
    private Double kmOcioso;
    private Boolean excedeu;

    public ViagemKmDTO(Double kmReal, Double kmPlanejado, Double kmOcioso, Boolean excedeu) {
        this.kmReal = kmReal;
        this.kmPlanejado = kmPlanejado;
        this.kmOcioso = kmOcioso;
        this.excedeu = excedeu;
    }

    public Double getKmReal() {
        return kmReal;
    }

    public Double getKmPlanejado() {
        return kmPlanejado;
    }

    public Double getKmOcioso() {
        return kmOcioso;
    }

    public Boolean getExcedeu() {
        return excedeu;
    }
}
