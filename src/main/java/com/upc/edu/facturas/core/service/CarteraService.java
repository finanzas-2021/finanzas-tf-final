package com.upc.edu.facturas.core.service;

import com.upc.edu.facturas.core.entity.Cartera;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;

import java.util.Optional;

public interface CarteraService {
    Page<Cartera> getAllCarteras(Pageable pageable);

    Optional<Cartera> getCarteraById(String carteraId);

    Cartera createCartera(Cartera cartera);

    Optional<Cartera> updateCartera(String carteraId, Cartera carteraRequest);

    ResponseEntity<?> deleteCartera(String carteraId);
}
