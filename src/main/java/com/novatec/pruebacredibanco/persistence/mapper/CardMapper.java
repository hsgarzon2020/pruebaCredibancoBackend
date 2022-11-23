package com.novatec.pruebacredibanco.persistence.mapper;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.persistence.entity.Card;
import org.mapstruct.Mapper;

import java.util.List;

@Mapper(componentModel = "spring")
public interface CardMapper {
    Card cardMapping(CardDomain cardDomain);

    CardDomain cardMapping(Card card);

    List<CardDomain> cardMapping (List<Card> card);

}
