package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.Tasa;
import com.upc.edu.facturas.core.repository.TasaRepository;
import com.upc.edu.facturas.core.service.TasaService;
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
public class TasaServiceImpl implements TasaService {
    @Autowired
    private TasaRepository tasaRepository;


    @Override
    public Page<Tasa> getAllTasas(Pageable pageable) {
        return tasaRepository.findAll(pageable);
    }

    @Override
    public Optional<Tasa> getTasaById(String tasaId) {
        return tasaRepository.findById(tasaId);
    }


    @Override
    @Transactional
    public Tasa createTasa(Tasa tasa) {
        return tasaRepository.save(tasa);
    }

    @Override
    @Transactional
    public Optional<Tasa> updateTasa(String tasaId, Tasa tasaRequest) {
        return tasaRepository.findById(tasaId).map(tasa -> {
            tasa.setValorTasa(tasaRequest.getValorTasa());
            tasa.setDiasAño(tasaRequest.getDiasAño());
            tasa.setFechaDescuento(tasaRequest.getFechaDescuento());
            tasa.setPeriodoCapitalizacion(tasaRequest.getPeriodoCapitalizacion());
            return tasaRepository.save(tasa);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteTasa(String tasaId) {
        return tasaRepository.findById(tasaId).map(tasa -> {
            tasaRepository.delete(tasa);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
