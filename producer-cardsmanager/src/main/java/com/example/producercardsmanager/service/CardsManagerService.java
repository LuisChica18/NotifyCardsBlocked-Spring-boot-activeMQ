package com.example.producercardsmanager.service;

import com.example.producercardsmanager.model.Card;

import java.util.Date;
import java.util.List;

/**
 * Implementation  of the CardsManagerService for the management of card of the application
 *     - Get All Card
 *     - Get Cards By the field blocked and sent
 *     - Update list cards of the table
 *     - Block a card by Id
 */
public interface CardsManagerService {

    /**
     * - Get All Card of the table CARD_APP_A
     * @return List<Card>
     */
    List<Card> getAllCardService();

    /**
     * - Get Cards By the field blocked and sent of the table CARD_APP_A
     * @param blocked Boolean
     * @param sentTo Boolean
     * @return List<Card>
     */
    List<Card> getCardsByBlockedAndSentService(Boolean blocked, Boolean sentTo);

    /**
     * - Update list cards of the table
     * @param cardList Boolean
     * @return List<Card>
     */
    List<Card> upDateList(List<Card> cardList);

    /**
     *  - Get all with expiry date time before
     * @param expiryDate Date
     * @return List<Card>
     */
    List<Card>  getAllWithExpiryDateTimeBefore(Date expiryDate);

    /**
     * - Block a card by Id
     * @param cardNumber String
     * @return List<Card>
     */
    List<Card> blockCarById(String cardNumber );
}