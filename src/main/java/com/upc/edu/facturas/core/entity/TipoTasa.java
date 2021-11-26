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
@Table(name = "tipo_tasas")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class TipoTasa extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Size(max = 8)
    @Column(name = "nombre_plazo", nullable = false, length = 8)
    private String nombreTipoTasa;
}
