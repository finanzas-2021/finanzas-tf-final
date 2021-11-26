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
@Table(name = "plazo_tasas")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class PlazoTasa extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Size(max = 30)
    @Column(name = "nombre_plazo", nullable = false, length = 30)
    private String nombrePlazo;

    @NotBlank
    @Column(name = "num_dias", nullable = false)
    private Integer numDias;
}
