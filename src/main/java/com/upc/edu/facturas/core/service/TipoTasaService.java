package com.upc.edu.facturas.core.service;

import com.upc.edu.facturas.core.entity.TipoTasa;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface TipoTasaService {
    Page<TipoTasa> getAllTipoTasas(Pageable pageable);

    Optional<TipoTasa> getTipoTasaById(String tipoTazaId);

    TipoTasa createTipoTasa(TipoTasa tipoTasa);

    Optional<TipoTasa> updateTipoTasa(String tipoTazaId, TipoTasa tipoTasaRequest);

    ResponseEntity<?> deleteTipoTasa(String tipoTazaId);
}
