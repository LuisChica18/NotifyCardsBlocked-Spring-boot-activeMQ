package com.example.consumercardsnotifier.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import java.util.Date;

@Data
@Entity(name = "card")
@Table(name = "CARD_BLOCKED_APP_B")
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