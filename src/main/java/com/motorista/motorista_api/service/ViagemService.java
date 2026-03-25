package com.motorista.motorista_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

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
    
    @Transactional
    public void deletar(Long id) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        viagemRepository.delete(viagem);
    }

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

    // SALVAR VIAGEM
    public Viagem salvar(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    // LISTAR VIAGENS
    public List<Viagem> listar() {
        return viagemRepository.findAll();
    }

    // INICIAR VIAGEM
    @Transactional
    public Viagem iniciarViagem(Long motoristaId, Long veiculoId, Long linhaId) {

        Motorista motorista = motoristaRepository.findById(motoristaId)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Linha linha = linhaRepository.findById(linhaId)
                .orElseThrow(() -> new RuntimeException("Linha não encontrada"));

        // Verifica se o motorista já está em uma viagem ativa
        if (viagemRepository.existsByMotoristaIdAndDataFimIsNull(motoristaId)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Motorista já está em uma viagem ativa"
            );
        }

        // Verifica se o veículo já está em uso
        if (viagemRepository.existsByVeiculoIdAndDataFimIsNull(veiculoId)) {
            throw new ResponseStatusException(
                HttpStatus.CONFLICT,
                "Veículo já está em uso"
            );
        }

        Viagem viagem = new Viagem();
        viagem.setMotorista(motorista);
        viagem.setVeiculo(veiculo);
        viagem.setLinha(linha);
        viagem.setDataInicio(LocalDateTime.now());
        viagem.setDataFim(null);

        return viagemRepository.save(viagem);
    }

    // FINALIZAR VIAGEM
    @Transactional
    public Viagem finalizarViagem(Long viagemId) {

        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        if (viagem.getDataFim() != null) {
            throw new RuntimeException("A viagem já foi finalizada");
        }

        viagem.setDataFim(LocalDateTime.now());

        return viagemRepository.save(viagem);
    }
}