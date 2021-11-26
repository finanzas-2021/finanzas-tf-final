package com.upc.edu.facturas.controller;

import com.upc.edu.facturas.core.entity.CostoFinal;
import com.upc.edu.facturas.core.service.CostoFinalService;
import com.upc.edu.facturas.map.CostoFinalResource;
import com.upc.edu.facturas.map.save.SaveCostoFinalResource;
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
@RequestMapping("/api/costo_final")
public class CostoFinalController {
    @Autowired
    private CostoFinalService costoFinalService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<CostoFinalResource>> getAllCostoFinal(Pageable pageable) {
        try {
            List<CostoFinalResource> costosFinales = costoFinalService.getAllCostoFinal(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (costosFinales.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(costosFinales, pageable, costosFinales.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{costoFinalId}")
    public ResponseEntity<CostoFinalResource> getCostoFinalById(@PathVariable(value = "costoFinalId") String costoFinalId) {
        try {
            Optional<CostoFinal> costoFinal = costoFinalService.getCostoFinalById(costoFinalId);
            return costoFinal.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping()
    public ResponseEntity<CostoFinalResource> createCostoFinal(@Valid @RequestBody SaveCostoFinalResource resource) {
        try {
            CostoFinal costoFinal = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(costoFinalService.createCostoFinal(costoFinal)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{costoFinalId}")
    public ResponseEntity<CostoFinalResource> updateCostoFinal(@PathVariable(name = "costoFinalId") String costoFinalId, @Valid @RequestBody SaveCostoFinalResource resource) {
        try {
            CostoFinal costoFinal = convertToEntity(resource);
            Optional<CostoFinal> costoFinalOptional = costoFinalService.updateCostoFinal(costoFinalId, costoFinal);
            return costoFinalOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{costoFinalId}")
    public ResponseEntity<?> deleteCostoFinal(@PathVariable(name = "costoFinalId") String costoFinalId) {
        try {
            return costoFinalService.deleteCostoFinal(costoFinalId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private CostoFinal convertToEntity(SaveCostoFinalResource resource) {
        return mapper.map(resource, CostoFinal.class);
    }

    private CostoFinalResource convertToResource(CostoFinal entity) {
        return mapper.map(entity, CostoFinalResource.class);
    }
}
