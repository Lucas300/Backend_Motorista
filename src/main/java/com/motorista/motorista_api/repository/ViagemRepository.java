package com.motorista.motorista_api.repository;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.motorista.motorista_api.model.Viagem;

public interface ViagemRepository extends JpaRepository<Viagem, Long> {

    boolean existsByMotoristaIdAndDataFimIsNull(Long motoristaId);

    boolean existsByVeiculoIdAndDataFimIsNull(Long veiculoId);

    List<Viagem> findByMotoristaId(Long motoristaId);

    List<Viagem> findByVeiculoId(Long veiculoId);

    List<Viagem> findByLinhaId(Long linhaId);
    
    @Query("""
    	    SELECT v FROM Viagem v
    	    WHERE v.linha.id = :linhaId
    	    AND v.dataInicio BETWEEN :inicio AND :fim
    	""")
    	List<Viagem> buscarPorLinhaEPeriodo(Long linhaId, LocalDateTime inicio, LocalDateTime fim);
    
    @Query("""
    	    SELECT v FROM Viagem v
    	    WHERE v.dataInicio BETWEEN :inicio AND :fim
    	""")
    	List<Viagem> buscarPorPeriodo(LocalDateTime inicio, LocalDateTime fim);
    
}