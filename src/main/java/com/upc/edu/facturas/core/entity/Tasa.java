package com.upc.edu.facturas.core.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "tasas")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Tasa extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Column(name = "valor_tasa", nullable = false)
    private Double valorTasa;

    @NotBlank
    @Column(name = "dias_año", nullable = false)
    private Integer diasAño;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_descuento", nullable = false)
    private Date fechaDescuento;

    @Size(max = 15)
    @Column(name = "periodo_capitalizacion", length = 15)
    private String periodoCapitalizacion;


}
