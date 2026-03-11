package com.motorista.motorista_api.model;

import jakarta.persistence.*;

@Entity
@Table(name = "paradas")
public class Parada {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private Double latitude;

    private Double longitude;

    private Integer ordem;

    @ManyToOne
    @JoinColumn(name = "linha_id")
    private Linha linha;

    public Parada() {
    }

    public Long getId() {
        return id;
    }

    public String getNome() {
        return nome;
    }

    public Double getLatitude() {
        return latitude;
    }

    public Double getLongitude() {
        return longitude;
    }

    public Integer getOrdem() {
        return ordem;
    }

    public Linha getLinha() {
        return linha;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public void setLatitude(Double latitude) {
        this.latitude = latitude;
    }

    public void setLongitude(Double longitude) {
        this.longitude = longitude;
    }

    public void setOrdem(Integer ordem) {
        this.ordem = ordem;
    }

    public void setLinha(Linha linha) {
        this.linha = linha;
    }
}