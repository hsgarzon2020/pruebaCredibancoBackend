package com.novatec.pruebacredibanco.app.dtos.response;

import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.NotNull;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class CardResponse {

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ResponseCode;
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String ResponseMessage;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String maskedPan;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String holder;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String id;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String phone;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String state;

    @JsonInclude(JsonInclude.Include.NON_NULL)
    private String hashId;

}
