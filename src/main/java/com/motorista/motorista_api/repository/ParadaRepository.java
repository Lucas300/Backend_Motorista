package com.motorista.motorista_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.motorista.motorista_api.model.Parada;

public interface ParadaRepository extends JpaRepository<Parada, Long> {
}
