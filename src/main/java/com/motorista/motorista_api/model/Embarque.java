package com.motorista.motorista_api.model;

import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonBackReference;

import jakarta.persistence.*;

@Entity
@Table(name = "embarques")
public class Embarque {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Double latitude;
    private Double longitude;

    private Integer quantidadePassageiros;

    private LocalDateTime dataHora;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "viagem_id")
    private Viagem viagem;

    @ManyToOne
    @JoinColumn(name = "parada_id")
    private Parada parada;

    public Long getId() {
        return id;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getQuantidadePassageiros() {
        return quantidadePassageiros;
    }

    public LocalDateTime getDataHora() {
        return dataHora;
    }

    public Viagem getViagem() {
        return viagem;
    }

    public Parada getParada() {
        return parada;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setQuantidadePassageiros(Integer quantidadePassageiros) {
        this.quantidadePassageiros = quantidadePassageiros;
    }

    public void setDataHora(LocalDateTime dataHora) {
        this.dataHora = dataHora;
    }

    public void setViagem(Viagem viagem) {
        this.viagem = viagem;
    }

    public void setParada(Parada parada) {
        this.parada = parada;
    }
}