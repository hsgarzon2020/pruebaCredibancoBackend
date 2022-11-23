package com.novatec.pruebacredibanco.app.dtos.response;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class GetAllCardsResponse {
    private String ResponseCode;
    private String ResponseMessage;
    private List<CardResponse> data;
}
