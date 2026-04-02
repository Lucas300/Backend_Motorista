package com.motorista.motorista_api.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.ArrayList;

import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.server.ResponseStatusException;

import com.motorista.motorista_api.dto.ViagemKmDTO;
import com.motorista.motorista_api.model.Linha;
import com.motorista.motorista_api.model.Localizacao;
import com.motorista.motorista_api.model.Motorista;
import com.motorista.motorista_api.model.Veiculo;
import com.motorista.motorista_api.model.Viagem;
import com.motorista.motorista_api.repository.LinhaRepository;
import com.motorista.motorista_api.repository.MotoristaRepository;
import com.motorista.motorista_api.repository.VeiculoRepository;
import com.motorista.motorista_api.repository.ViagemRepository;
import com.motorista.motorista_api.utils.GeoUtils;

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

    @Transactional
    public void deletar(Long id) {
        Viagem viagem = viagemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        viagemRepository.delete(viagem);
    }

    public Viagem salvar(Viagem viagem) {
        return viagemRepository.save(viagem);
    }

    public List<Viagem> listar() {
        return viagemRepository.findAll();
    }

    @Transactional
    public Viagem iniciarViagem(Long motoristaId, Long veiculoId, Long linhaId) {

        Motorista motorista = motoristaRepository.findById(motoristaId)
                .orElseThrow(() -> new RuntimeException("Motorista não encontrado"));

        Veiculo veiculo = veiculoRepository.findById(veiculoId)
                .orElseThrow(() -> new RuntimeException("Veículo não encontrado"));

        Linha linha = linhaRepository.findById(linhaId)
                .orElseThrow(() -> new RuntimeException("Linha não encontrada"));

        if (viagemRepository.existsByMotoristaIdAndDataFimIsNull(motoristaId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Motorista já está em uma viagem ativa");
        }

        if (viagemRepository.existsByVeiculoIdAndDataFimIsNull(veiculoId)) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "Veículo já está em uso");
        }

        Viagem viagem = new Viagem();
        viagem.setMotorista(motorista);
        viagem.setVeiculo(veiculo);
        viagem.setLinha(linha);
        viagem.setDataInicio(LocalDateTime.now());

        return viagemRepository.save(viagem);
    }

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

    public double calcularKmViagem(Viagem viagem) {

        List<Localizacao> lista = viagem.getLocalizacoes();

        if (lista == null || lista.size() < 2) return 0.0;

        double total = 0.0;

        for (int i = 0; i < lista.size() - 1; i++) {

            Localizacao atual = lista.get(i);
            Localizacao proxima = lista.get(i + 1);

            double distancia = GeoUtils.calcularDistancia(
                    atual.getLatitude().doubleValue(),
                    atual.getLongitude().doubleValue(),
                    proxima.getLatitude().doubleValue(),
                    proxima.getLongitude().doubleValue()
            );

            // 🔥 FILTRO ANTI-RUÍDO GPS
            if (distancia > 0.01 && distancia < 1) {
                total += distancia;
            }
        }

        return total;
    }
    
    public ViagemKmDTO obterResumoKmViagem(Long viagemId) {

        Viagem viagem = viagemRepository.findById(viagemId)
                .orElseThrow(() -> new RuntimeException("Viagem não encontrada"));

        double kmReal = calcularKmViagem(viagem);
        double kmPlanejado = viagem.getLinha().getKmPlanejado();

        double kmOcioso = kmReal - kmPlanejado;
        boolean excedeu = kmReal > kmPlanejado;

        return new ViagemKmDTO(kmReal, kmPlanejado, kmOcioso, excedeu);
    }
    
    public List<ViagemKmDTO> listarViagensExcedidasNoMes(int mes, int ano) {

        LocalDateTime inicio = LocalDateTime.of(ano, mes, 1, 0, 0);

        LocalDateTime fim = inicio.withDayOfMonth(inicio.toLocalDate().lengthOfMonth())
                                  .withHour(23).withMinute(59).withSecond(59);

        List<Viagem> viagens = viagemRepository.buscarPorPeriodo(inicio, fim);

        List<ViagemKmDTO> excedidas = new ArrayList<>();

        for (Viagem v : viagens) {

            double kmReal = calcularKmViagem(v);
            double kmPlanejado = v.getLinha().getKmPlanejado();

            if (kmReal > kmPlanejado) {

                double kmOcioso = kmReal - kmPlanejado;

                excedidas.add(new ViagemKmDTO(
                    kmReal,
                    kmPlanejado,
                    kmOcioso,
                    true
                ));
            }
        }

        return excedidas;
    }
}