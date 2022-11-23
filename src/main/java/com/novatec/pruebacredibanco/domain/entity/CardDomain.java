package com.novatec.pruebacredibanco.domain.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.CreditCardNumber;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardDomain {

    @NotNull
    private String id;

    @NotNull
    private Long pan;

    @NotNull
    private String hashId;

    @NotNull
    private String holder;

    @NotNull
    private Long phone;

    @NotNull
    private String state;

    @NotNull
    private Integer validationNumber;

    @NotNull
    private String CardType;

}
