package com.motorista.motorista_api.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.motorista.motorista_api.model.Empresa;

public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
}