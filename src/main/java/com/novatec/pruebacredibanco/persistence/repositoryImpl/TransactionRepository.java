package com.novatec.pruebacredibanco.persistence.repositoryImpl;

import com.novatec.pruebacredibanco.domain.entity.CardDomain;
import com.novatec.pruebacredibanco.domain.entity.TransactionDomain;
import com.novatec.pruebacredibanco.domain.repository.TransactionRepositoryDomain;
import com.novatec.pruebacredibanco.persistence.entity.Transaction;
import com.novatec.pruebacredibanco.persistence.mapper.TransactionMapper;
import com.novatec.pruebacredibanco.persistence.repositoryJpa.TransactionRepositoryJpa;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class TransactionRepository implements TransactionRepositoryDomain {

    @Autowired
    TransactionRepositoryJpa transactionRepositoryJpa;

    @Autowired
    TransactionMapper transactionMapper;

    @Override
    public void createTransaction(TransactionDomain transactionDomain) throws Exception {
        transactionRepositoryJpa.saveAndFlush(transactionMapper.transactionMapping(transactionDomain));
    }

    @Override
    public List<TransactionDomain> findTransanctionByHashId(String hashId) throws Exception {
        List<Transaction> transactions = transactionRepositoryJpa.findByCardId(hashId);
        if (!transactions.isEmpty()) {
            return transactionMapper.transactionMapping(transactions);
        } else {
            return new ArrayList<TransactionDomain>();
        }
    }

    @Override
    public void cancelTransaction(TransactionDomain transactionDomain) throws Exception {
        transactionRepositoryJpa.updateState(transactionMapper.transactionMapping(transactionDomain).getState(),transactionMapper.transactionMapping(transactionDomain).getTransactionId());
    }

    @Override
    public Optional<TransactionDomain> findTransactionByReference(Long referenceNumber) throws Exception {
        Optional<Transaction> transactionDomain = transactionRepositoryJpa.findByReferenceNumber(referenceNumber);
        if (transactionDomain.isPresent()) {
            return Optional.of(transactionMapper.transactionMapping(transactionDomain.get()));
        } else {
            return Optional.empty();
        }
    }
}
