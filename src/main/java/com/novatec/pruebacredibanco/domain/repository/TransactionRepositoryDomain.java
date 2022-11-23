package com.novatec.pruebacredibanco.domain.repository;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;

import java.util.List;
import java.util.Optional;

public interface TransactionRepositoryDomain {

    void createTransaction(TransactionDomain transactionDomain) throws Exception;
    List<TransactionDomain> findTransanctionByHashId(String hashId) throws Exception;

    void cancelTransaction(TransactionDomain transactionDomain) throws Exception;

    Optional<TransactionDomain> findTransactionByReference(Long referenceNumber) throws Exception;


}
