package com.motorista.motorista_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorista.motorista_api.model.ParadaReal;

public interface ParadaRealRepository extends JpaRepository<ParadaReal, Long> {

    List<ParadaReal> findByViagemId(Long viagemId);

}