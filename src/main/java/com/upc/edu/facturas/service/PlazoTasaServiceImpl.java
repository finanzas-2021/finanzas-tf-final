package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.PlazoTasa;
import com.upc.edu.facturas.core.repository.PlazoTasaRepository;
import com.upc.edu.facturas.core.service.PlazoTasaService;
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
public class PlazoTasaServiceImpl implements PlazoTasaService {
    @Autowired
    private PlazoTasaRepository plazoTasaRepository;


    @Override
    public Page<PlazoTasa> getAllPlazoTasas(Pageable pageable) {
        return plazoTasaRepository.findAll(pageable);
    }

    @Override
    public Optional<PlazoTasa> getPlazoTasaById(String plazoTasaId) {
        return plazoTasaRepository.findById(plazoTasaId);
    }


    @Override
    @Transactional
    public PlazoTasa createPlazoTasa(PlazoTasa plazoTasa) {
        return plazoTasaRepository.save(plazoTasa);
    }

    @Override
    @Transactional
    public Optional<PlazoTasa> updatePlazoTasa(String plazoTasaId, PlazoTasa plazoTasaRequest) {
        return plazoTasaRepository.findById(plazoTasaId).map(plazoTasa -> {
            plazoTasa.setNombrePlazo(plazoTasaRequest.getNombrePlazo());
            plazoTasa.setNumDias(plazoTasaRequest.getNumDias());
            return plazoTasaRepository.save(plazoTasa);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deletePlazoTasa(String plazoTasaId) {
        return plazoTasaRepository.findById(plazoTasaId).map(plazoTasa -> {
            plazoTasaRepository.delete(plazoTasa);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
