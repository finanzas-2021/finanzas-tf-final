package com.upc.edu.facturas.controller;

import com.upc.edu.facturas.core.entity.Tasa;
import com.upc.edu.facturas.core.service.TasaService;
import com.upc.edu.facturas.map.TasaResource;
import com.upc.edu.facturas.map.save.SaveTasaResource;
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
@RequestMapping("/api/tasas")
public class TasaController {
    @Autowired
    private TasaService tasaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<TasaResource>> getAllTasas(Pageable pageable) {
        try {
            List<TasaResource> tasas = tasaService.getAllTasas(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (tasas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(tasas, pageable, tasas.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{tasaId}")
    public ResponseEntity<TasaResource> getTasaById(@PathVariable(value = "tasaId") String tasaId) {
        try {
            Optional<Tasa> tasa = tasaService.getTasaById(tasaId);
            return tasa.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping()
    public ResponseEntity<TasaResource> createTasa(@Valid @RequestBody SaveTasaResource resource) {
        try {
            Tasa tasa = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(tasaService.createTasa(tasa)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{tasaId}")
    public ResponseEntity<TasaResource> updateTasa(@PathVariable(name = "tasaId") String tasaId, @Valid @RequestBody SaveTasaResource resource) {
        try {
            Tasa tasa = convertToEntity(resource);
            Optional<Tasa> tasaOptional = tasaService.updateTasa(tasaId, tasa);
            return tasaOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{tasaId}")
    public ResponseEntity<?> deleteTasa(@PathVariable(name = "tasaId") String tasaId) {
        try {
            return tasaService.deleteTasa(tasaId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private Tasa convertToEntity(SaveTasaResource resource) {
        return mapper.map(resource, Tasa.class);
    }

    private TasaResource convertToResource(Tasa entity) {
        return mapper.map(entity, TasaResource.class);
    }
}
