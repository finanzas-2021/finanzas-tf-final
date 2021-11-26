package com.upc.edu.facturas.service;

import com.upc.edu.facturas.core.entity.Cartera;
import com.upc.edu.facturas.core.repository.CarteraRepository;
import com.upc.edu.facturas.core.service.CarteraService;
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
public class CarteraServiceImpl implements CarteraService {
    @Autowired
    private CarteraRepository carteraRepository;


    @Override
    public Page<Cartera> getAllCarteras(Pageable pageable) {
        return carteraRepository.findAll(pageable);
    }

    @Override
    public Optional<Cartera> getCarteraById(String carteraId) {
        return carteraRepository.findById(carteraId);
    }


    @Override
    @Transactional
    public Cartera createCartera(Cartera cartera) {
        return carteraRepository.save(cartera);
    }

    @Override
    @Transactional
    public Optional<Cartera> updateCartera(String carteraId, Cartera carteraRequest) {
        return carteraRepository.findById(carteraId).map(cartera -> {
            cartera.setTotalValorRecibido(carteraRequest.getTotalValorRecibido());
            cartera.setTotalTCEA(carteraRequest.getTotalTCEA());
            return carteraRepository.save(cartera);
        });
    }

    @Override
    @Transactional
    public ResponseEntity<?> deleteCartera(String carteraId) {
        return carteraRepository.findById(carteraId).map(cartera -> {
            carteraRepository.delete(cartera);
            return ResponseEntity.ok().build();
        }).orElse(ResponseEntity.notFound().build());
    }
}
