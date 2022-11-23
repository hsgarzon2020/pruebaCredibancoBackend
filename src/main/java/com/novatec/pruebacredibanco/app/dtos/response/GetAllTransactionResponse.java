package com.novatec.pruebacredibanco.app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllTransactionResponse{

    private String ResponseCode;
    private String ResponseMessage;
    private List<TransactionResponse> data;
}
