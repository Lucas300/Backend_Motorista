package com.motorista.motorista_api.dto;

public class LinhaKmDTO {

    private Double kmTotal;
    private Double kmPlanejado;
    private Boolean excedeu;
    private String status;

    // ✅ construtor antigo (não quebra nada)
    public LinhaKmDTO(Double kmTotal, Double kmPlanejado, Boolean excedeu) {
        this.kmTotal = kmTotal;
        this.kmPlanejado = kmPlanejado;
        this.excedeu = excedeu;
    }

    // 🔥 construtor novo (melhor)
    public LinhaKmDTO(Double kmTotal, Double kmPlanejado, Boolean excedeu, String status) {
        this.kmTotal = kmTotal;
        this.kmPlanejado = kmPlanejado;
        this.excedeu = excedeu;
        this.status = status;
    }

    public Double getKmTotal() { return kmTotal; }
    public Double getKmPlanejado() { return kmPlanejado; }
    public Boolean getExcedeu() { return excedeu; }
    public String getStatus() { return status; }

	public void setKmTotal(Double kmTotal) {
		this.kmTotal = kmTotal;
	}

	public void setKmPlanejado(Double kmPlanejado) {
		this.kmPlanejado = kmPlanejado;
	}

	public void setExcedeu(Boolean excedeu) {
		this.excedeu = excedeu;
	}

	public void setStatus(String status) {
		this.status = status;
	}
    
    
}