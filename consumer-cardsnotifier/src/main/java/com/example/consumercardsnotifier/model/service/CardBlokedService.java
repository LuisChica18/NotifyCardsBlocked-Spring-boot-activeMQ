package com.example.consumercardsnotifier.model.service;

import com.example.consumercardsnotifier.model.Card;

import java.util.List;

/**
 * Interface of the CardBlokedService for the management of card of the application
 *     - Save a list Card
 *     - Update a list Card
 *     - Get a list Card by the field sendTo
 */
public interface CardBlokedService {

    /**
     * - Save a list Card  in the table CARD_BLOCKED_APP_B
     * @param cardBlokedList List<Card>
     */
    void setListCard(List<Card> cardBlokedList);

    /**
     * - Update a list Card Bloked in the table CARD_BLOCKED_APP_B
     * @param cardList List<Card>
     * @return Boolean
     */
    List<Card> upDateList(List<Card> cardList);

    /**
     * - Get a list Card by the field ToSend in the table CARD_BLOCKED_APP_B
     * @param sendTo List<Card>
     * @return Boolean
     */
    List<Card> getListCardsToSend(Boolean sendTo);
}