package com.upc.edu.facturas.core.service;

import com.upc.edu.facturas.core.entity.CostoFinal;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CostoFinalService {
    Page<CostoFinal> getAllCostoFinal(Pageable pageable);

    Optional<CostoFinal> getCostoFinalById(String costoFinalId);

    CostoFinal createCostoFinal(CostoFinal costoFinal);

    Optional<CostoFinal> updateCostoFinal(String costoFinalId, CostoFinal costoFinalRequest);

    ResponseEntity<?> deleteCostoFinal(String costoFinalId);
}
