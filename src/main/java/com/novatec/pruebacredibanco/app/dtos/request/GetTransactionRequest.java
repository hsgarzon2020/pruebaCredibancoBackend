package com.novatec.pruebacredibanco.app.dtos.request;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class GetTransactionRequest {

    @NotNull
    private String hashId;

}


