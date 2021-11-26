package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.CostoFinal;
import com.upc.edu.facturas.core.repository.CostoFinalRepository;
import com.upc.edu.facturas.core.service.CostoFinalService;
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
public class CostoFinalServiceImpl implements CostoFinalService {
    @Autowired
    private CostoFinalRepository costoFinalRepository;


    @Override
    public Page<CostoFinal> getAllCostoFinal(Pageable pageable) {
        return costoFinalRepository.findAll(pageable);
    }

    @Override
    public Optional<CostoFinal> getCostoFinalById(String costoFinalId) {
        return costoFinalRepository.findById(costoFinalId);
    }


    @Override
    @Transactional
    public CostoFinal createCostoFinal(CostoFinal costoFinal) {
        return costoFinalRepository.save(costoFinal);
    }

    @Override
    @Transactional
    public Optional<CostoFinal> updateCostoFinal(String costoFinalId, CostoFinal costoFinalRequest) {
        return costoFinalRepository.findById(costoFinalId).map(costoFinal -> {
            costoFinal.setMotivo(costoFinalRequest.getMotivo());
            costoFinal.setMonto(costoFinalRequest.getMonto());
            return costoFinalRepository.save(costoFinal);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCostoFinal(String costoFinalId) {
        return costoFinalRepository.findById(costoFinalId).map(costoFinal -> {
            costoFinalRepository.delete(costoFinal);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
