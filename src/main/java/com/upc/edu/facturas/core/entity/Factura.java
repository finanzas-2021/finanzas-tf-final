package com.upc.edu.facturas.core.entity;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import java.io.Serializable;
import java.util.Date;

@Entity
@Table(name = "facturas")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Factura extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    private String nombreFactura;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_emision", nullable = false)
    private Date fecha_emision;

    @Temporal(TemporalType.DATE)
    @Column(name = "fecha_pago", nullable = false)
    private Date fecha_pago;
}
