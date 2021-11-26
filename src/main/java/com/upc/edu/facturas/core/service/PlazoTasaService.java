package com.upc.edu.facturas.core.service;

import com.upc.edu.facturas.core.entity.PlazoTasa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface PlazoTasaService {
    Page<PlazoTasa> getAllPlazoTasas(Pageable pageable);

    Optional<PlazoTasa> getPlazoTasaById(String plazoTazaId);

    PlazoTasa createPlazoTasa(PlazoTasa plazoTasa);

    Optional<PlazoTasa> updatePlazoTasa(String plazoTazaId, PlazoTasa plazoTasaRequest);

    ResponseEntity<?> deletePlazoTasa(String plazoTazaId);
}
