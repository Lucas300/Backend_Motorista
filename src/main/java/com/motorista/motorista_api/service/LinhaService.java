package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.dto.LinhaKmDTO;
import com.motorista.motorista_api.model.Linha;
import com.motorista.motorista_api.model.Parada;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.LinhaRepository;
import com.motorista.motorista_api.repository.ViagemRepository;

@Service
public class LinhaService {

    private final LinhaRepository linhaRepository;
    private final ViagemRepository viagemRepository;
    private final ViagemService viagemService;

    public LinhaService(LinhaRepository linhaRepository,
                        ViagemRepository viagemRepository,
                        ViagemService viagemService) {
        this.linhaRepository = linhaRepository;
        this.viagemRepository = viagemRepository;
        this.viagemService = viagemService;
    }

    public List<Linha> listarTodas() {
        return linhaRepository.findAll();
    }

    public Linha buscarPorId(Long id) {
        return linhaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Linha não encontrada"));
    }

    public Linha salvar(Linha linha) {
        return linhaRepository.save(linha);
    }

    public List<Parada> buscarRotaPlanejada(Long linhaId) {

        Linha linha = buscarPorId(linhaId);

        List<Parada> paradas = linha.getParadas();

        paradas.sort((p1, p2) -> Integer.compare(p1.getOrdem(), p2.getOrdem()));

        return paradas;
    }

    // 🔥 MÉTODO CORRIGIDO
    public double calcularKmTotalLinha(Long linhaId) {

        List<Viagem> viagens = viagemRepository.findByLinhaId(linhaId);

        double total = 0.0;

        for (Viagem v : viagens) {
            total += viagemService.calcularKmViagem(v);
        }

        return total;
    }

    public boolean excedeuKm(Long linhaId) {

        Linha linha = buscarPorId(linhaId);

        double kmTotal = calcularKmTotalLinha(linhaId);

        return kmTotal > linha.getKmPlanejado();
    }
    
    public LinhaKmDTO obterResumoKm(Long linhaId) {

        Linha linha = buscarPorId(linhaId);

        double kmTotal = calcularKmTotalLinha(linhaId);
        double kmPlanejado = linha.getKmPlanejado();

        boolean excedeu = kmTotal > kmPlanejado;

        return new LinhaKmDTO(kmTotal, kmPlanejado, excedeu);
    }
}