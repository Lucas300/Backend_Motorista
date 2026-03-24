package com.motorista.motorista_api.model;

import java.time.LocalDateTime;
import java.util.List;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;

@Entity
@Table(name = "viagens")
public class Viagem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private LocalDateTime dataInicio;

    private LocalDateTime dataFim;

    private String status;
    
    @ManyToOne
    @JoinColumn(name = "linha_id")
    private Linha linha;

    @ManyToOne
    @JoinColumn(name = "motorista_id")
    private Motorista motorista;

    @ManyToOne
    @JoinColumn(name = "veiculo_id")
    private Veiculo veiculo;
    
    @OneToMany(mappedBy = "viagem", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Localizacao> localizacoes;

    public Linha getLinha() {
		return linha;
	}

	public List<Localizacao> getLocalizacoes() {
		return localizacoes;
	}

	public void setLocalizacoes(List<Localizacao> localizacoes) {
		this.localizacoes = localizacoes;
	}

	public void setLinha(Linha linha) {
		this.linha = linha;
	}

	public Viagem() {
    }

    public Long getId() {
        return id;
    }

    public LocalDateTime getDataInicio() {
        return dataInicio;
    }

    public LocalDateTime getDataFim() {
        return dataFim;
    }

    public String getStatus() {
        return status;
    }

    public Motorista getMotorista() {
        return motorista;
    }

    public Veiculo getVeiculo() {
        return veiculo;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setDataInicio(LocalDateTime dataInicio) {
        this.dataInicio = dataInicio;
    }

    public void setDataFim(LocalDateTime dataFim) {
        this.dataFim = dataFim;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public void setMotorista(Motorista motorista) {
        this.motorista = motorista;
    }

    public void setVeiculo(Veiculo veiculo) {
        this.veiculo = veiculo;
    }
}