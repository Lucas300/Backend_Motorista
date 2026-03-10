package com.motorista.motorista_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.motorista.motorista_api.model.Viagem;

@Repository
public interface ViagemRepository extends JpaRepository<Viagem, Long> {

}