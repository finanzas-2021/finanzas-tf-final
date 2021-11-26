package com.upc.edu.facturas.core.repository;

import com.upc.edu.facturas.core.entity.PlazoTasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PlazoTasaRepository extends JpaRepository<PlazoTasa, String> {
}
