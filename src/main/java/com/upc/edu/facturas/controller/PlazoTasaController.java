package com.upc.edu.facturas.controller;


import com.upc.edu.facturas.core.entity.PlazoTasa;
import com.upc.edu.facturas.core.service.PlazoTasaService;
import com.upc.edu.facturas.map.PlazoTasaResource;
import com.upc.edu.facturas.map.save.SavePlazoTasaResource;
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
@RequestMapping("/api/plazo_tasas")
public class PlazoTasaController {
    @Autowired
    private PlazoTasaService plazoTasaService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<PlazoTasaResource>> getAllPlazoTasas(Pageable pageable) {
        try {
            List<PlazoTasaResource> plazoTasas = plazoTasaService.getAllPlazoTasas(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (plazoTasas.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(plazoTasas, pageable, plazoTasas.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{plazoTasaId}")
    public ResponseEntity<PlazoTasaResource> getPlazoTasaById(@PathVariable(value = "plazoTasaId") String plazoTasaId) {
        try {
            Optional<PlazoTasa> plazoTasa = plazoTasaService.getPlazoTasaById(plazoTasaId);
            return plazoTasa.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping()
    public ResponseEntity<PlazoTasaResource> createPlazoTasa(@Valid @RequestBody SavePlazoTasaResource resource) {
        try {
            PlazoTasa plazoTasa = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(plazoTasaService.createPlazoTasa(plazoTasa)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{plazoTasaId}")
    public ResponseEntity<PlazoTasaResource> updatePlazoTasa(@PathVariable(name = "plazoTasaId") String plazoTasaId, @Valid @RequestBody SavePlazoTasaResource resource) {
        try {
            PlazoTasa plazoTasa = convertToEntity(resource);
            Optional<PlazoTasa> plazoTasaOptional = plazoTasaService.updatePlazoTasa(plazoTasaId, plazoTasa);
            return plazoTasaOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{plazoTasaId}")
    public ResponseEntity<?> deletePlazoTasa(@PathVariable(name = "plazoTasaId") String plazoTasaId) {
        try {
            return plazoTasaService.deletePlazoTasa(plazoTasaId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private PlazoTasa convertToEntity(SavePlazoTasaResource resource) {
        return mapper.map(resource, PlazoTasa.class);
    }

    private PlazoTasaResource convertToResource(PlazoTasa entity) {
        return mapper.map(entity, PlazoTasaResource.class);
    }
}
