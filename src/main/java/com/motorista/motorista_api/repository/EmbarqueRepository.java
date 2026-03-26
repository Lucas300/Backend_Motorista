package com.motorista.motorista_api.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.motorista.motorista_api.model.Embarque;

public interface EmbarqueRepository extends JpaRepository<Embarque, Long> {
    List<Embarque> findByViagemId(Long viagemId);

}