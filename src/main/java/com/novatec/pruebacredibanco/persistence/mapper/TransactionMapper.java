package com.novatec.pruebacredibanco.persistence.mapper;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;
import com.novatec.pruebacredibanco.persistence.entity.Card;
import com.novatec.pruebacredibanco.persistence.entity.Transaction;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import java.util.List;

@Mapper(componentModel = "spring")
public interface TransactionMapper {

    @Mapping(target = "card", source = "cardDomain")
    Transaction transactionMapping(TransactionDomain transactionDomain);

    TransactionDomain transactionMapping(Transaction transaction);

    List<TransactionDomain> transactionMapping(List<Transaction> transaction);

    Card cardMapping(CardDomain cardDomain);



}
