package com.upc.edu.facturas.core.repository;


import com.upc.edu.facturas.core.entity.TipoTasa;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TipoTasaRepository extends JpaRepository<TipoTasa, String> {
}
