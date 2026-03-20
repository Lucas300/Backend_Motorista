package com.motorista.motorista_api.model;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonManagedReference;
import com.fasterxml.jackson.annotation.JsonProperty;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "linhas")
public class Linha {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    @ManyToOne
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "linha", cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Parada> paradas;
    

    @Column(name = "km_planejado", nullable = false)
    @JsonProperty("km_planejado") // <-- garante que o JSON vai mapear para este campo
    private Double kmPlanejado;

	public Double getKmPlanejado() {
		return kmPlanejado;
	}

	public void setKmPlanejado(Double kmPlanejado) {
		this.kmPlanejado = kmPlanejado;
	}

	public Linha() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Empresa getEmpresa() {
        return empresa;
    }

    public List<Parada> getParadas() {
        return paradas;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setEmpresa(Empresa empresa) {
        this.empresa = empresa;
    }

    public void setParadas(List<Parada> paradas) {
        this.paradas = paradas;
    }
}