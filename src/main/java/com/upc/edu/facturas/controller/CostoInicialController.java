package com.upc.edu.facturas.controller;

import com.upc.edu.facturas.core.entity.CostoInicial;
import com.upc.edu.facturas.core.service.CostoInicialService;
import com.upc.edu.facturas.map.CostoInicialResource;
import com.upc.edu.facturas.map.save.SaveCostoInicialResource;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Slf4j
@RestController
//@CrossOrigin
@RequestMapping("/api/costo_inicial")
public class CostoInicialController {
    @Autowired
    private CostoInicialService costoInicialService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<CostoInicialResource>> getAllCostoInicial(Pageable pageable) {
        try {
            List<CostoInicialResource> costosIniciales = costoInicialService.getAllCostoInicial(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (costosIniciales.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(costosIniciales, pageable, costosIniciales.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{costoInicialId}")
    public ResponseEntity<CostoInicialResource> getCostoInicialById(@PathVariable(value = "costoInicialId") String costoInicialId) {
        try {
            Optional<CostoInicial> costoInicial = costoInicialService.getCostoInicialById(costoInicialId);
            return costoInicial.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping()
    public ResponseEntity<CostoInicialResource> createCostoInicial(@Valid @RequestBody SaveCostoInicialResource resource) {
        try {
            CostoInicial costoInicial = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(costoInicialService.createCostoInicial(costoInicial)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{costoInicialId}")
    public ResponseEntity<CostoInicialResource> updateCostoInicial(@PathVariable(name = "costoInicialId") String costoInicialId, @Valid @RequestBody SaveCostoInicialResource resource) {
        try {
            CostoInicial costoInicial = convertToEntity(resource);
            Optional<CostoInicial> costoInicialOptional = costoInicialService.updateCostoInicial(costoInicialId, costoInicial);
            return costoInicialOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{costoInicialId}")
    public ResponseEntity<?> deleteCostoInicial(@PathVariable(name = "costoInicialId") String costoInicialId) {
        try {
            return costoInicialService.deleteCostoInicial(costoInicialId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private CostoInicial convertToEntity(SaveCostoInicialResource resource) {
        return mapper.map(resource, CostoInicial.class);
    }

    private CostoInicialResource convertToResource(CostoInicial entity) {
        return mapper.map(entity, CostoInicialResource.class);
    }
}
