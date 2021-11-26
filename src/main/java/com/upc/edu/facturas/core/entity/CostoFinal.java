package com.upc.edu.facturas.core.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;

@Entity
@Table(name = "costo_final")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class CostoFinal extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Size(max = 20)
    @Column(name = "motivo", nullable = false, length = 20)
    private String motivo;

    @NotBlank
    @Column(name = "monto", nullable = false)
    private Double monto;
}
