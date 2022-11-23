package com.novatec.pruebacredibanco.domain.util;

import org.apache.commons.lang3.StringUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Base64;

public class Util {

    public static final String CARD_STATUS_CREATED = "CREADA";
    public static final String CARD_STATUS_ACTIVATE = "ENROLADA";
    public static final String CARD_STATUS_INACTIVE = "INACTIVA";
    public static final String TRANSACTION_STATUS_APPROVED = "Aprobada";
    public static final String TRANSACTION_STATUS_CANCEL = "Cancelada";
    public static final String CODE_SUCCESS = "00";
    public static final String CODE_FAILED = "01";
    public static final String CODE_VALIDATION_NUMBER_INVALID = "02";
    public static final String CODE_CANNOT_CANCEL_TRANSACTION = "02";
    public static final String MESSAGE_CANNOT_CANCEL_TRANSACTION = "No se puede anular transaccion";
    public static final String MESSAGE_INVALID_REFERENCE = "Numero de referencia invalido";
    public static final String CODE_STATE_CARD_INVALID = "02";
    public static final String MESSAGE_CARD_NOT_FOUND = "Tarjeta no existe";
    public static final String MESSAGE_STATE_CARD_INVALID = "Tarjeta no enrolada";
    public static final String MESSAGE_DELETE_CARD_NOT_FOUND = "No se ha eliminado tarjeta";
    public static final String MESSAGE_VALIDATION_NUMBER_INVALID = "Número de validación inválido";
    public static final String MESSAGE_FAILED = "Fallido";
    public static final String MESSAGE_SUCCESS = "Exito";
    public static final String MESSAGE_SUCCESS_TRANSACTION = "Compra exitosa";

    public static final String MESSAGE_SUCCESS_CANCEL_TRANSACTION = "Compra anulada";

    public static final String MESSAGE_REFERENCE_NUMBER_INVALID = "Compra anulada";

    public static int setValidationNumber() {
        return (int) ((Math.random() * (100 - 1)) + 1);
    }

    public static String hashPan(Long pan) throws IOException, NoSuchAlgorithmException {

        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMddHHmmss");
        String creationDate = sdf.format(System.currentTimeMillis());

        SecureRandom rand = new SecureRandom();
        ByteArrayOutputStream digest = new ByteArrayOutputStream();
        byte[] nonceByte = new byte[20];
        rand.nextBytes(nonceByte);
        digest.write(nonceByte);
        String nonce = toSHA1(digest.toString() + pan + creationDate);
        String[] stringBuilder = nonce.split("/");
        nonce = String.join("", stringBuilder);
        return nonce;

    }

    public static String toSHA1(String userPass) throws NoSuchAlgorithmException, IOException {
        ByteArrayOutputStream digest = new ByteArrayOutputStream();
        MessageDigest md = MessageDigest.getInstance("SHA1");
        byte[] data = md.digest(userPass.getBytes());
        digest.write(data);
        return Base64.getEncoder().encodeToString(digest.toByteArray());
    }

    public static String maskedPan(Long pan) {
        final String s = pan.toString().replaceAll("\\D", "");

        final int start = 6;
        final int end = s.length() - 4;
        final String overlay = StringUtils.repeat("*", end - start);

        return StringUtils.overlay(s, overlay, start, end);
    }
}
