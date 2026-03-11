package com.motorista.motorista_api.service;

import java.util.List;

import org.springframework.stereotype.Service;

import com.motorista.motorista_api.model.Empresa;
import com.motorista.motorista_api.repository.EmpresaRepository;

@Service
public class EmpresaService {

    private final EmpresaRepository empresaRepository;

    public EmpresaService(EmpresaRepository empresaRepository) {
        this.empresaRepository = empresaRepository;
    }

    // Listar todas as empresas
    public List<Empresa> listarTodas() {
        return empresaRepository.findAll();
    }

    // Buscar empresa por ID
    public Empresa buscarPorId(Long id) {
        return empresaRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Empresa não encontrada"));
    }

    // Salvar empresa
    public Empresa salvar(Empresa empresa) {
        return empresaRepository.save(empresa);
    }
}