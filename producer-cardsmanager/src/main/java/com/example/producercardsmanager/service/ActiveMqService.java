package com.example.producercardsmanager.service;

import com.example.producercardsmanager.model.Card;

import java.util.List;

/**
 * Interface of the ActiveMqService can contain different services related with our ActiveMQ, in this demo just have one but could have more
 *     - Send Cards Blocked to CardsNotifier by ActiveMq
 *
 */
public interface ActiveMqService {

    /**
     - Send Cards Blocked to CardsNotifier by ActiveMq
     * @param cardBlokedList List<Card>
     */
    void sendCardsBlockedApp(List<Card> cardBlokedList);
}