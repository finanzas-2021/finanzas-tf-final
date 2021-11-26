package com.upc.edu.facturas.core.service;

import com.upc.edu.facturas.core.entity.CostoInicial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CostoInicialService {
    Page<CostoInicial> getAllCostoInicial(Pageable pageable);

    Optional<CostoInicial> getCostoInicialById(String costoInicialId);

    CostoInicial createCostoInicial(CostoInicial costoInicial);

    Optional<CostoInicial> updateCostoInicial(String costoInicialId, CostoInicial costoInicialRequest);

    ResponseEntity<?> deleteCostoInicial(String costoInicialId);
}
