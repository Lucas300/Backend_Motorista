package com.motorista.motorista_api.repository;


import org.springframework.data.jpa.repository.JpaRepository;

import com.motorista.motorista_api.model.Motorista;

public interface MotoristaRepository extends JpaRepository<Motorista, Long> {

}