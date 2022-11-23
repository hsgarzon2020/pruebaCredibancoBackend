package com.novatec.pruebacredibanco.app.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDto {

    @NotNull
    private Long id;

    @NotNull
    private String hashId;

    @NotNull
    private Long referenceNumber;

    @NotNull
    private Float totalPurchase;

    @NotNull
    private String addressPurchase;

    @NotNull
    private String state;

    @NotNull
    private Date creationDate;

}
