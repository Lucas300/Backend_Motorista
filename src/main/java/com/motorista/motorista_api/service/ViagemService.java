package com.motorista.motorista_api.service;

import java.time.LocalDateTime;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Linha;
import com.motorista.motorista_api.model.Motorista;
import com.motorista.motorista_api.model.Veiculo;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.LinhaRepository;
import com.motorista.motorista_api.repository.MotoristaRepository;
import com.motorista.motorista_api.repository.VeiculoRepository;
import com.motorista.motorista_api.repository.ViagemRepository;

@Service
public class ViagemService {

    private final ViagemRepository viagemRepository;
    private final MotoristaRepository motoristaRepository;
    private final VeiculoRepository veiculoRepository;
    private final LinhaRepository linhaRepository;

    public ViagemService(
            ViagemRepository viagemRepository,
            MotoristaRepository motoristaRepository,
            VeiculoRepository veiculoRepository,
            LinhaRepository linhaRepository) {

        this.viagemRepository = viagemRepository;
        this.motoristaRepository = motoristaRepository;
        this.veiculoRepository = veiculoRepository;
        this.linhaRepository = linhaRepository;
    }

    public Viagem iniciarViagem(Long motoristaId, Long veiculoId, Long linhaId) {

        Motorista motorista = motoristaRepository.findById(motoristaId)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Linha linha = linhaRepository.findById(linhaId)
                .orElseThrow(() -> new RuntimeException("Linha não encontrada"));

        Viagem viagem = new Viagem();
        viagem.setMotorista(motorista);
        viagem.setVeiculo(veiculo);
        viagem.setLinha(linha);
        viagem.setDataInicio(LocalDateTime.now());

        return viagemRepository.save(viagem);
    }
}