package com.novatec.pruebacredibanco.persistence.repositoryJpa;

import com.novatec.pruebacredibanco.persistence.entity.Card;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Optional;

@Repository
public interface CardRepositoryJpa extends JpaRepository<Card, String> {
    public Optional<Card> findByPan(Long pan);

    @Query("SELECT u.validationNumber FROM Card u WHERE u.hashId= ?1")
    int findValidationNumberByHashId(String hashId);

    @Modifying
    @Transactional
    @Query(value = "UPDATE Card u SET u.state= :state WHERE u.hashId = :hashId")
    void updateState(@Param(value = "state") String state, @Param(value = "hashId") String hashId);
}
