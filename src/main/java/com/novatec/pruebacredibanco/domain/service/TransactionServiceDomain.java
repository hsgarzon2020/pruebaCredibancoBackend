package com.novatec.pruebacredibanco.domain.service;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;
import com.novatec.pruebacredibanco.domain.repository.CardRepositoryDomain;
import com.novatec.pruebacredibanco.domain.repository.TransactionRepositoryDomain;
import com.novatec.pruebacredibanco.domain.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;

@Service
public class TransactionServiceDomain {

    @Autowired
    TransactionRepositoryDomain transactionRepositoryDomain;

    @Autowired
    CardRepositoryDomain cardRepositoryDomain;

    public TransactionDomain createTransaction(TransactionDomain transactionDomain)
            throws Exception {

        Date date = new Date();

        Optional<CardDomain> cardDomain = cardRepositoryDomain.findCardById(transactionDomain.getHashId());
        if (cardDomain.isPresent()) {
            if (cardDomain.get().getState().equals(Util.CARD_STATUS_ACTIVATE)) {
                transactionDomain.setState(Util.TRANSACTION_STATUS_APPROVED);
                transactionDomain.setCardDomain(cardDomain.get());
                transactionDomain.setCreationDate(date);
                transactionRepositoryDomain.createTransaction(transactionDomain);
            } else {
                throw new CardInactiveException(HttpStatus.BAD_REQUEST, CardInactiveException.MESSAGE_CARD_INACTIVE);
            }
        } else {
            throw new CardNotFoundException(HttpStatus.BAD_REQUEST, CardNotFoundException.MESSAGE_CARD_NOT_FOUND);
        }
        return transactionDomain;
    }

    public TransactionDomain cancelTransaction(TransactionDomain transactionDomain)
            throws Exception {


        Optional<CardDomain> cardDomain = cardRepositoryDomain.findCardById(transactionDomain.getHashId());
        Optional<TransactionDomain> optionalTransactionDomain
                = transactionRepositoryDomain.findTransactionByReference(transactionDomain.getReferenceNumber());
        Long dateTransactionBD = optionalTransactionDomain.get().getCreationDate().getTime() - 5000;
        Long date = System.currentTimeMillis();

        if (cardDomain.isPresent()) {
            if (optionalTransactionDomain.isPresent()) {
                if (dateTransactionBD <= date) {
                    transactionDomain.setState(Util.TRANSACTION_STATUS_CANCEL);
                    transactionDomain.setCardDomain(cardDomain.get());
                    transactionDomain.setTransactionId(optionalTransactionDomain.get().getTransactionId());
                    transactionRepositoryDomain.cancelTransaction(transactionDomain);
                } else {
                    throw new CannotCancelTransactionException(HttpStatus.BAD_REQUEST, CannotCancelTransactionException.MESSAGE_CANCEL_EXCEPTION);
                }
            } else {
                throw new ReferenceNumberInvalid(HttpStatus.BAD_REQUEST, ReferenceNumberInvalid.MESSAGE_REFERENCE_NUMBER_INVALID);
            }
        } else {
            throw new CardNotFoundException(HttpStatus.BAD_REQUEST, CardNotFoundException.MESSAGE_CARD_NOT_FOUND);
        }
        return transactionDomain;
    }

    public List<TransactionDomain> getTransactionByHashId(String hashId) throws Exception {
        List<TransactionDomain> transactions;

        if (validateHashId(hashId)) {
            transactions = transactionRepositoryDomain.findTransanctionByHashId(hashId);
        } else {
            throw new CardNotFoundException(HttpStatus.BAD_REQUEST, CardNotFoundException.MESSAGE_CARD_NOT_FOUND);
        }
        return transactions;
    }

    public Boolean validateHashId(String hashId) throws Exception {
        Optional<CardDomain> cardDomain = cardRepositoryDomain.findCardById(hashId);
        if (cardDomain.isPresent()) {
            return true;
        }
        return false;
    }
}
