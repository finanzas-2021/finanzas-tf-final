package com.upc.edu.facturas.controller;

import com.upc.edu.facturas.core.entity.Cartera;
import com.upc.edu.facturas.core.service.CarteraService;
import com.upc.edu.facturas.map.CarteraResource;
import com.upc.edu.facturas.map.save.SaveCarteraResource;
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
@RequestMapping("/api/carteras")
public class CarteraController {
    @Autowired
    private CarteraService carteraService;

    @Autowired
    private ModelMapper mapper;

    @GetMapping()
    public ResponseEntity<Page<CarteraResource>> getAllCarteras(Pageable pageable) {
        try {
            List<CarteraResource> carteras = carteraService.getAllCarteras(pageable)
                    .getContent().stream()
                    .map(this::convertToResource)
                    .collect(Collectors.toList());
            if (carteras.isEmpty()) {
                return ResponseEntity.noContent().build();
            }
            return ResponseEntity.ok(new PageImpl<>(carteras, pageable, carteras.size()));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @GetMapping("/{carteraId}")
    public ResponseEntity<CarteraResource> getEmpresaById(@PathVariable(value = "carteraId") String carteraId) {
        try {
            Optional<Cartera> cartera = carteraService.getCarteraById(carteraId);
            return cartera.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    @PostMapping()
    public ResponseEntity<CarteraResource> createCartera(@Valid @RequestBody SaveCarteraResource resource) {
        try {
            Cartera cartera = convertToEntity(resource);
            return ResponseEntity.status(HttpStatus.CREATED).body(convertToResource(carteraService.createCartera(cartera)));
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @PutMapping("/{carteraId}")
    public ResponseEntity<CarteraResource> updateCartera(@PathVariable(name = "carteraId") String carteraId, @Valid @RequestBody SaveCarteraResource resource) {
        try {
            Cartera cartera = convertToEntity(resource);
            Optional<Cartera> carteraOptional = carteraService.updateCartera(carteraId, cartera);
            return carteraOptional.map(value -> ResponseEntity.ok(convertToResource(value)))
                    .orElseGet(() -> ResponseEntity
                            .notFound().build());
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }

    @DeleteMapping("/{carteraId}")
    public ResponseEntity<?> deleteCartera(@PathVariable(name = "carteraId") String carteraId) {
        try {
            return carteraService.deleteCartera(carteraId);
        } catch (Exception e) {
            return ResponseEntity.internalServerError().build();
        }
    }


    // Auto Mapper
    private Cartera convertToEntity(SaveCarteraResource resource) {
        return mapper.map(resource, Cartera.class);
    }

    private CarteraResource convertToResource(Cartera entity) {
        return mapper.map(entity, CarteraResource.class);
    }
}
