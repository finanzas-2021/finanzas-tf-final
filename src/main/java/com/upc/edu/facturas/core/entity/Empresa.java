package com.upc.edu.facturas.core.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.hibernate.annotations.NaturalId;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.PositiveOrZero;
import javax.validation.constraints.Size;
import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "empresas")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Empresa extends AuditModel implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Size(max = 50)
    @Column(name = "razon_social", nullable = false, length = 50)
    private String razonSocial;

    @NotBlank
    @Size(min = 11, max = 11)
    @Column(name = "ruc", nullable = false, length = 11)
    private String ruc;
}
