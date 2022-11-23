package com.novatec.pruebacredibanco.persistence.repositoryImpl;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;
import com.novatec.pruebacredibanco.domain.repository.CardRepositoryDomain;
import com.novatec.pruebacredibanco.persistence.entity.Card;
import com.novatec.pruebacredibanco.persistence.mapper.CardMapper;
import com.novatec.pruebacredibanco.persistence.repositoryJpa.CardRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class CardRepository implements CardRepositoryDomain {

    @Autowired
    CardRepositoryJpa cardRepositoryJpa;

    @Autowired
    CardMapper cardMapper;

    @Override
    public void createCard(CardDomain cardDomain) throws Exception {
        cardRepositoryJpa.saveAndFlush(cardMapper.cardMapping(cardDomain));
    }

    @Override
    public Optional<CardDomain> findCardByPan(Long pan) throws Exception {

        Optional<Card> cardDomain = cardRepositoryJpa.findByPan(pan);
        if (cardDomain.isPresent()) {
            return Optional.of(cardMapper.cardMapping(cardDomain.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public void activateCard(CardDomain cardDomain) throws Exception {
        cardRepositoryJpa.updateState(cardMapper.cardMapping(cardDomain).getState(), cardMapper.cardMapping(cardDomain).getHashId());
    }

    @Override
    public Optional<CardDomain> findCardById(String id) throws Exception {
        Optional<Card> cardDomain = cardRepositoryJpa.findById(id);
        if (cardDomain.isPresent()) {
            return Optional.of(cardMapper.cardMapping(cardDomain.get()));
        } else {
            return Optional.empty();
        }
    }

    @Override
    public Integer findValidateNumberById(String id) throws Exception {
        return cardRepositoryJpa.findValidationNumberByHashId(id);
    }

    @Override
    public void inactivateCard(CardDomain cardDomain) throws Exception {
        cardRepositoryJpa.updateState(cardMapper.cardMapping(cardDomain).getState(), cardMapper.cardMapping(cardDomain).getHashId());
    }

    @Override
    public List<CardDomain> getCards() throws Exception {

        List<Card> cards = cardRepositoryJpa.findAll();
        if (!cards.isEmpty()) {
            return cardMapper.cardMapping(cards);
        } else {
            return new ArrayList<CardDomain>();
        }
    }


}
