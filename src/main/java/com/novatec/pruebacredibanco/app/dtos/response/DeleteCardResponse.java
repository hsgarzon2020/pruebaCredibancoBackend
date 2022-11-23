package com.novatec.pruebacredibanco.app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DeleteCardResponse {

    private String ResponseCode;
    private String ResponseMessage;
}
