package com.novatec.pruebacredibanco.domain.repository;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;

import java.util.List;
import java.util.Optional;

public interface CardRepositoryDomain {

    void createCard(CardDomain cardDomain) throws Exception;

    Optional<CardDomain> findCardByPan(Long pan) throws Exception;

    void activateCard(CardDomain cardDomain) throws Exception;

    Optional<CardDomain> findCardById(String id) throws Exception;

    Integer findValidateNumberById(String id) throws Exception;

    void inactivateCard(CardDomain cardDomain) throws Exception;

    List<CardDomain> getCards() throws Exception;

}
