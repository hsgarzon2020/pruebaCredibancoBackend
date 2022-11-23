package com.novatec.pruebacredibanco.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDto {

    @NotNull
    private String id;

    @NotNull
    private Long pan;

    @NotNull
    private String hashId;

    @NotNull
    private String holder;

    @NotNull
    private String phone;

    @NotNull
    private String state;

    @NotNull
    private Integer validationNumber;

    @NotNull
    private String CardType;
}