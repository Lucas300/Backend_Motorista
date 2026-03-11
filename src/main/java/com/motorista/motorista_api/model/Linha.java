package com.motorista.motorista_api.model;

import jakarta.persistence.*;
import java.util.List;

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
    private List<Parada> paradas;

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