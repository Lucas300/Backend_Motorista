package com.motorista.motorista_api.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import com.motorista.motorista_api.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    boolean existsByMotoristaIdAndDataFimIsNull(Long motoristaId);

    boolean existsByVeiculoIdAndDataFimIsNull(Long veiculoId);

    List<Viagem> findByMotoristaId(Long motoristaId);

    List<Viagem> findByVeiculoId(Long veiculoId);
}