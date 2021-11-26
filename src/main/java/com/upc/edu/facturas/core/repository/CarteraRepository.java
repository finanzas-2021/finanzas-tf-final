package com.upc.edu.facturas.core.repository;

import com.upc.edu.facturas.core.entity.Cartera;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CarteraRepository extends JpaRepository<Cartera, String> {
}
