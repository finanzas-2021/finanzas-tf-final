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
@Table(name = "usuarios")
@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class Usuario extends AuditModel implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private String id;

    @NotBlank
    @Size(max = 25)
    @Column(name = "nombre", nullable = false, length = 15)
    private String nombre;

    @NotBlank
    @Size(max = 15)
    @Column(name = "apellido_paterno", nullable = false, length = 15)
    private String apellidoPaterno;

    @NotBlank
    @Size(max = 15)
    @Column(name = "apellido_materno", nullable = false, length = 15)
    private String apellidoMaterno;

    @NotBlank
    @Size(max = 9)
    @Column(name = "telefono", nullable = false, length = 9)
    private String telefono;

    @NotBlank
    @Size(max = 40)
    @Column(name = "email", nullable = false, length = 40)
    private String email;

    @NotBlank
    @Size(min= 7, max = 20)
    @Column(name = "password", nullable = false, length = 20)
    private String password;
}
