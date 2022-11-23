package com.novatec.pruebacredibanco.app.dtos.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CancelTransactionRequest {

    @NotNull
    private String hashId;

    @NotNull
    private Long referenceNumber;

    @NotNull
    private Float totalPurchase;

}
