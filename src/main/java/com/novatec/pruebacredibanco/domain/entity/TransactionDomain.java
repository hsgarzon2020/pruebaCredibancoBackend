package com.novatec.pruebacredibanco.domain.entity;


import com.novatec.pruebacredibanco.persistence.entity.Card;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;
import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionDomain {

    @NotNull
    private Long transactionId;

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

    private Date creationDate;

    @NotNull
    private CardDomain cardDomain;

}
