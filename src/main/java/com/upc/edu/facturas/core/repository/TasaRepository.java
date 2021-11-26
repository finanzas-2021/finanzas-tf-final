package com.upc.edu.facturas.core.repository;

import com.upc.edu.facturas.core.entity.Tasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TasaRepository extends JpaRepository<Tasa, String> {
}
