package com.novatec.pruebacredibanco.domain.service;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.domain.repository.CardRepositoryDomain;
import com.novatec.pruebacredibanco.domain.util.CardNotFoundException;
import com.novatec.pruebacredibanco.domain.util.DuplicityException;
import com.novatec.pruebacredibanco.domain.util.Util;
import com.novatec.pruebacredibanco.domain.util.ValidateNumberInvalidException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class CardServiceDomain {

    @Autowired
    CardRepositoryDomain cardRepositoryDomain;


    public CardDomain createCard(CardDomain cardDomain)
            throws Exception {

        if (!validateDuplicity(cardDomain.getPan())) {
            cardDomain.setHashId(Util.hashPan(cardDomain.getPan()));
            cardDomain.setValidationNumber(Util.setValidationNumber());
            cardDomain.setState(Util.CARD_STATUS_CREATED);
            cardRepositoryDomain.createCard(cardDomain);
        } else {
            throw new DuplicityException(HttpStatus.BAD_REQUEST, DuplicityException.MESSAGE_DUPLICITY_CARD);
        }
        return cardDomain;
    }

    public Boolean validateDuplicity(Long pan) throws Exception {
        Optional<CardDomain> cardDomain = cardRepositoryDomain.findCardByPan(pan);
        if (cardDomain.isPresent()) {
            return true;
        }
        return false;
    }

    public CardDomain activateCard(CardDomain cardDomain)
            throws Exception {

        if (validateHashId(cardDomain.getHashId())) {
            if (validateNumber(cardDomain)) {
                cardDomain = cardRepositoryDomain.findCardById(cardDomain.getHashId()).get();
                cardDomain.setState(Util.CARD_STATUS_ACTIVATE);
                cardRepositoryDomain.activateCard(cardDomain);
            } else {
                throw new ValidateNumberInvalidException(HttpStatus.BAD_REQUEST, ValidateNumberInvalidException.MESSAGE_VALIDATE_NUMBER);
            }
        } else {
            throw new CardNotFoundException(HttpStatus.BAD_REQUEST, CardNotFoundException.MESSAGE_CARD_NOT_FOUND);
        }
        return cardDomain;
    }

    public CardDomain getCard(CardDomain cardDomain) throws Exception {

        if (validateHashId(cardDomain.getHashId())) {
            cardDomain = cardRepositoryDomain.findCardById(cardDomain.getHashId()).get();
        } else {
            throw new CardNotFoundException(HttpStatus.BAD_REQUEST, CardNotFoundException.MESSAGE_CARD_NOT_FOUND);
        }
        return cardDomain;
    }

    public List<CardDomain> getCards() throws Exception {
        return cardRepositoryDomain.getCards();
    }

    public CardDomain deleteCard(CardDomain cardDomain)
            throws Exception {

        if (validateHashId(cardDomain.getHashId())) {
            cardDomain = cardRepositoryDomain.findCardById(cardDomain.getHashId()).get();
            cardDomain.setState(Util.CARD_STATUS_INACTIVE);
            cardRepositoryDomain.inactivateCard(cardDomain);
        } else {
            throw new CardNotFoundException(HttpStatus.BAD_REQUEST, CardNotFoundException.MESSAGE_CARD_NOT_FOUND);
        }
        return cardDomain;
    }


    public Boolean validateHashId(String hashId) throws Exception {
        Optional<CardDomain> cardDomain = cardRepositoryDomain.findCardById(hashId);
        if (cardDomain.isPresent()) {
            return true;
        }
        return false;
    }

    public Boolean validateNumber(CardDomain cardDomain) throws Exception {
        if (cardRepositoryDomain.findValidateNumberById(cardDomain.getHashId()) == cardDomain.getValidationNumber()) {
            return true;
        }
        return false;
    }


}
