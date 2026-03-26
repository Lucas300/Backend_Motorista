package com.motorista.motorista_api.service;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.dto.EmbarqueDTO;
import com.motorista.motorista_api.model.Embarque;
import com.motorista.motorista_api.model.Parada;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.EmbarqueRepository;
import com.motorista.motorista_api.repository.ParadaRepository;
import com.motorista.motorista_api.repository.ViagemRepository;

@Service
public class EmbarqueService {

    private final EmbarqueRepository embarqueRepository;
    private final ViagemRepository viagemRepository;
    private final ParadaRepository paradaRepository;
    
    public List<Embarque> listarPorViagem(Long viagemId) {

        return embarqueRepository.findByViagemId(viagemId);
    }

    public EmbarqueService(EmbarqueRepository embarqueRepository,
                           ViagemRepository viagemRepository,
                           ParadaRepository paradaRepository) {
        this.embarqueRepository = embarqueRepository;
        this.viagemRepository = viagemRepository;
        this.paradaRepository = paradaRepository;
    }

    public Embarque registrarEmbarque(EmbarqueDTO dto) {

        Viagem viagem = viagemRepository.findById(dto.getViagemId())
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        Embarque embarque = new Embarque();
        embarque.setViagem(viagem);
        embarque.setLatitude(dto.getLatitude());
        embarque.setLongitude(dto.getLongitude());
        embarque.setQuantidadePassageiros(dto.getQuantidadePassageiros());
        embarque.setDataHora(LocalDateTime.now());

        if (dto.getParadaId() != null) {
            Parada parada = paradaRepository.findById(dto.getParadaId())
                    .orElseThrow(() -> new RuntimeException("Parada não encontrada"));
            embarque.setParada(parada);
        }

        Embarque salvo = embarqueRepository.save(embarque);

        viagem.getEmbarques().add(salvo);

        return salvo;
    }
}