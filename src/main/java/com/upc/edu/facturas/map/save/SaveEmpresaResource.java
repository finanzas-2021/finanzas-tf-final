package com.upc.edu.facturas.map.save;

import lombok.*;
import org.hibernate.annotations.NaturalId;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@Getter
@Setter
@ToString
@RequiredArgsConstructor
public class SaveEmpresaResource {
    @NaturalId
    @Size(max = 50)
    private String razonSocial;

    @NotBlank
    @Size(min = 11, max = 11)
    private String ruc;

}
