package com.motorista.motorista_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.motorista.motorista_api.model.Linha;

public interface LinhaRepository extends JpaRepository<Linha, Long> {
}