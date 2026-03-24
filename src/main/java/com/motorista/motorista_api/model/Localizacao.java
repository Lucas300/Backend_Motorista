package com.motorista.motorista_api.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Index;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(
    name = "localizacao",
    indexes = {
        @Index(name = "idx_viagem_datahora", columnList = "viagem_id, dataHora")
    }
)
public class Localizacao {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // Relacionamento com a viagem
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "viagem_id", nullable = false)
    @JsonIgnore
    private Viagem viagem;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal latitude;

    @Column(nullable = false, precision = 9, scale = 6)
    private BigDecimal longitude;

    @Column(nullable = true)
    private Double velocidade; // km/h

    @Column(nullable = false)
    private LocalDateTime dataHora;

    @Column(nullable = true)
    private Double precisao; // metros (accuracy do GPS)

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public Viagem getViagem() {
		return viagem;
	}

	public void setViagem(Viagem viagem) {
		this.viagem = viagem;
	}

	public BigDecimal getLatitude() {
		return latitude;
	}

	public void setLatitude(BigDecimal latitude) {
		this.latitude = latitude;
	}

	public BigDecimal getLongitude() {
		return longitude;
	}

	public void setLongitude(BigDecimal longitude) {
		this.longitude = longitude;
	}

	public Double getVelocidade() {
		return velocidade;
	}

	public void setVelocidade(Double velocidade) {
		this.velocidade = velocidade;
	}

	public LocalDateTime getDataHora() {
		return dataHora;
	}

	public void setDataHora(LocalDateTime dataHora) {
		this.dataHora = dataHora;
	}

	public Double getPrecisao() {
		return precisao;
	}

	public void setPrecisao(Double precisao) {
		this.precisao = precisao;
	}     
}