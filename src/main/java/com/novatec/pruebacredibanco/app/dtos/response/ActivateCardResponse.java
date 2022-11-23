package com.novatec.pruebacredibanco.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class ActivateCardResponse {

    private String ResponseCode;
    private String ResponseMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String maskedPan;

}
