package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.Empresa;
import com.upc.edu.facturas.core.repository.EmpresaRepository;
import com.upc.edu.facturas.core.service.EmpresaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Slf4j
@Service
@Transactional(readOnly = true)
public class EmpresaServiceImpl implements EmpresaService {
    @Autowired
    private EmpresaRepository empresaRepository;


    @Override
    public Page<Empresa> getAllEmpresas(Pageable pageable) {
        return empresaRepository.findAll(pageable);
    }

    @Override
    public Optional<Empresa> getEmpresaById(String empresaId) {
        return empresaRepository.findById(empresaId);
    }

    @Override
    public Optional<Empresa> getEmpresaByRuc(String ruc) {
        return empresaRepository.findByRuc(ruc);
    }

    @Override
    @Transactional
    public Empresa createEmpresa(Empresa empresa) {
        return empresaRepository.save(empresa);
    }

    @Override
    @Transactional
    public Optional<Empresa> updateEmpresa(String empresaId, Empresa empresaRequest) {
        return empresaRepository.findById(empresaId).map(empresa -> {
            empresa.setRazonSocial(empresaRequest.getRazonSocial());
            empresa.setRuc(empresaRequest.getRuc());
            return empresaRepository.save(empresa);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteEmpresa(String empresaId) {
        return empresaRepository.findById(empresaId).map(empresa -> {
            empresaRepository.delete(empresa);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }

}
