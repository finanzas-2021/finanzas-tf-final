package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.CostoInicial;
import com.upc.edu.facturas.core.repository.CostoInicialRepository;
import com.upc.edu.facturas.core.service.CostoInicialService;
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
public class CostoInicialServiceImpl implements CostoInicialService {
    @Autowired
    private CostoInicialRepository costoInicialRepository;


    @Override
    public Page<CostoInicial> getAllCostoInicial(Pageable pageable) {
        return costoInicialRepository.findAll(pageable);
    }

    @Override
    public Optional<CostoInicial> getCostoInicialById(String costoInicialId) {
        return costoInicialRepository.findById(costoInicialId);
    }


    @Override
    @Transactional
    public CostoInicial createCostoInicial(CostoInicial costoInicial) {
        return costoInicialRepository.save(costoInicial);
    }

    @Override
    @Transactional
    public Optional<CostoInicial> updateCostoInicial(String costoInicialId, CostoInicial costoInicialRequest) {
        return costoInicialRepository.findById(costoInicialId).map(costoInicial -> {
            costoInicial.setMotivo(costoInicialRequest.getMotivo());
            costoInicial.setMonto(costoInicialRequest.getMonto());
            return costoInicialRepository.save(costoInicial);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCostoInicial(String costoInicialId) {
        return costoInicialRepository.findById(costoInicialId).map(costoInicial -> {
            costoInicialRepository.delete(costoInicial);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
