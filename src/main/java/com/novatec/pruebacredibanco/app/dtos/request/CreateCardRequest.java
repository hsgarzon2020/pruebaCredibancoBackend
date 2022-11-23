package com.novatec.pruebacredibanco.app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.*;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateCardRequest {

    @NotNull
    private Long pan;

    @NotNull
    private String holder;

    @NotNull
    @Size(min = 10, max = 15)
    private String id;

    @NotNull
    @Pattern(regexp = "^(Crédito|Débito)$")
    private String CardType;

    @NotNull
    @Size(min = 10, max = 10)
    private String phone;

}
