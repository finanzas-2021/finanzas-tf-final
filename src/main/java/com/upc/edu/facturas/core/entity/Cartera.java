package com.upc.edu.facturas.core.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import java.io.Serializable;

@Entity
@Table(name = "carteras")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Cartera extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Column(name = "total_valor_recibido", nullable = false)
    private Double totalValorRecibido;

    @NotBlank
    @Column(name = "total_tcea", nullable = false)
    private Double totalTCEA;
}
