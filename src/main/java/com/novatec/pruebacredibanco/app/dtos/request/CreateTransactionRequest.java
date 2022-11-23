package com.novatec.pruebacredibanco.app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CreateTransactionRequest {

    @NotNull
    private String hashId;

    @NotNull
    private Long referenceNumber;

    @NotNull
    private Float totalPurchase;

    @NotNull
    private String addressPurchase;

}
