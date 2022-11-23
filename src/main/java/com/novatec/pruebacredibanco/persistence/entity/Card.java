package com.novatec.pruebacredibanco.persistence.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Entity
public class Card {

    @Id
    private String hashId;
    private String id;
    private Long pan;
    private String holder;
    private Long phone;
    private String state;
    private Integer validationNumber;
    private String cardType;

    //@OneToMany(mappedBy = "card", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    //private List<Transaction> transactions;
}
