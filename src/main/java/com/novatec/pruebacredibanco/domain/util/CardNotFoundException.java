package com.novatec.pruebacredibanco.domain.util;

import lombok.Data;
import org.springframework.http.HttpStatus;
@Data
public class CardNotFoundException extends Exception{
    private final HttpStatus code;
    private final String reason;
    public static final String MESSAGE_SERVER_ERROR = "Request execution error, contact support to report your problem";
    public static final String MESSAGE_CARD_NOT_FOUND = "Tarjeta no existe";

    public CardNotFoundException(HttpStatus httpStatus, String cause) {
        this.code = httpStatus;
        this.reason = cause;
    }
}
