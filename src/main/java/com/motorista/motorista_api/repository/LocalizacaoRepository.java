package com.motorista.motorista_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motorista.motorista_api.model.Localizacao;

@Repository
public interface LocalizacaoRepository extends JpaRepository<Localizacao, Long> {
	List<Localizacao> findByViagemIdOrderByDataHoraAsc(Long viagemId);
}