package com.example.producercardsmanager.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

/**
 *  - The Card class is annotated with @Card, indicating that it is a JPA entity. For lack of a @Table annotation, it is assumed
 *    that this entity will be mapped to a table named CARD_BLOCKED_APP_B.
 *  - The Employee´s id property is annotated with @Id so that JPA will recognize it as the object’s ID.
 *  - The properties call name, card, cardHolderName, blocked, sentTo are annotate as @Column, it is mean that will be mapped to
 *    columns that share, on the other hand, card and cardHolderName properties can have max length 20, can´t be null as indicated
 *    in the annotation @Column.
 *
 */
@Data
@Entity(name = "card")
@Table(name = "CARD_APP_A")
public class Card {

    @Id
    public Integer id;

    @Column(name = "CARD", length = 20, nullable = false, unique = true)
    public String card;

    @Column(name = "CARD_HOLDER_NAME", length = 20, nullable = false, unique = false)
    public String cardHolderName;

    @Column(name = "EXPIRY_DATE", nullable = false, unique = false)
    public Date expiryDate;

    @Column(name = "BLOCKED", nullable = false, unique = false)
    public Boolean blocked;

    @Column(name = "SENT_TO", nullable = false, unique = false)
    public Boolean sentTo;
}