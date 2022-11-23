package com.novatec.pruebacredibanco.config;

import com.novatec.pruebacredibanco.domain.repository.CardRepositoryDomain;
import com.novatec.pruebacredibanco.domain.repository.TransactionRepositoryDomain;
import com.novatec.pruebacredibanco.persistence.repositoryImpl.CardRepository;
import com.novatec.pruebacredibanco.persistence.repositoryImpl.TransactionRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ManagerBean {

    @Bean
    public CardRepositoryDomain cardRepositoryDomain() {
        return new CardRepository();
    }

    @Bean
    public TransactionRepositoryDomain transactionRepositoryDomain() {
        return new TransactionRepository();
    }
}
