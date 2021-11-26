package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.TipoTasa;
import com.upc.edu.facturas.core.repository.TipoTasaRepository;
import com.upc.edu.facturas.core.service.TipoTasaService;
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
public class TipoTasaServiceImpl implements TipoTasaService {
    @Autowired
    private TipoTasaRepository tipoTasaRepository;


    @Override
    public Page<TipoTasa> getAllTipoTasas(Pageable pageable) {
        return tipoTasaRepository.findAll(pageable);
    }

    @Override
    public Optional<TipoTasa> getTipoTasaById(String tipoTasaId) {
        return tipoTasaRepository.findById(tipoTasaId);
    }


    @Override
    @Transactional
    public TipoTasa createTipoTasa(TipoTasa tipoTasa) {
        return tipoTasaRepository.save(tipoTasa);
    }

    @Override
    @Transactional
    public Optional<TipoTasa> updateTipoTasa(String tipoTasaId, TipoTasa tipoTasaRequest) {
        return tipoTasaRepository.findById(tipoTasaId).map(tipoTasa -> {
            tipoTasa.setNombreTipoTasa(tipoTasaRequest.getNombreTipoTasa());
            return tipoTasaRepository.save(tipoTasa);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteTipoTasa(String tipoTasaId) {
        return tipoTasaRepository.findById(tipoTasaId).map(tipoTasa -> {
            tipoTasaRepository.delete(tipoTasa);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
