package com.novatec.pruebacredibanco.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransactionResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long transactionId;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String addressPurchase;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Date creationDate;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Long referenceNumber;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String state;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private Float totalPurchase;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String cardId;


}
