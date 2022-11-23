package com.novatec.pruebacredibanco.persistence.repositoryJpa;

import com.novatec.pruebacredibanco.persistence.entity.Transaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

public interface TransactionRepositoryJpa extends JpaRepository<Transaction, String> {

    @Query(value = "SELECT u FROM Transaction u WHERE u.card.hashId = :cardId")
    List<Transaction> findByCardId(@Param(value = "cardId")String cardId);

    Optional<Transaction> findByReferenceNumber(Long referenceNumber);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Transaction u SET u.state= :state WHERE u.transactionId = :transactionId")
    void updateState(@Param(value = "state") String state, @Param(value = "transactionId") Long transactionId);
}
