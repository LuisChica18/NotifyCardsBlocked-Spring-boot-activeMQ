package com.example.producercardsmanager.service.impl;

import com.example.producercardsmanager.jms.ActiveMqProductor;
import com.example.producercardsmanager.model.Card;
import com.example.producercardsmanager.service.ActiveMqService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Implementation of the ActiveMqService can contain different services related with our ActiveMQ, in this demo just have one but could have more
 *     - Send Cards Blocked to CardsNotifier by ActiveMq
 *
 */
@Service
public class ActiveMqServiceImp implements ActiveMqService {

    @Autowired
    ActiveMqProductor activeMqProductor;

    /**
     * - Send Cards Blocked to CardsNotifier by ActiveMq
     *
     * @param cardBlokedList List<Card>
     */
    @Override
    public void sendCardsBlockedApp(List<Card> cardBlokedList) {
        activeMqProductor.productorCardsBlocked(cardBlokedList);
    }
}