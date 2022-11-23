package com.novatec.pruebacredibanco.domain.util;

import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
public class DuplicityException extends Exception {
    private final HttpStatus code;
    private final String reason;
    public static final String MESSAGE_SERVER_ERROR = "Request execution error, contact support to report your problem";
    public static final String MESSAGE_DUPLICITY_CARD = "Tarjeta duplicada";

    public DuplicityException(HttpStatus httpStatus, String cause) {
        this.code = httpStatus;
        this.reason = cause;
    }

}
