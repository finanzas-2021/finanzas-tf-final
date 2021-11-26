package com.upc.edu.facturas.core.service;

import com.upc.edu.facturas.core.entity.Tasa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TasaService {
    Page<Tasa> getAllTasas(Pageable pageable);

    Optional<Tasa> getTasaById(String tasaId);

    Tasa createTasa(Tasa tasa);

    Optional<Tasa> updateTasa(String tasaId, Tasa tasaRequest);

    ResponseEntity<?> deleteTasa(String tasaId);
}
