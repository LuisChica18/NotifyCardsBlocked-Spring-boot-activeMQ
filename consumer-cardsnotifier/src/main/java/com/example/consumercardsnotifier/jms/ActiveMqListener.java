package com.example.consumercardsnotifier.jms;

import com.example.consumercardsnotifier.model.Card;
import com.example.consumercardsnotifier.model.service.CardBlokedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.annotation.JmsListener;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * - The follow component there are the listener of the different queue of ActiveMq server.
 * - In this demo there is just a listener to the queue cardsclocked but could be more.
 */
@Component
public class ActiveMqListener {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    CardBlokedService cardBlokedService;

    /**
     * - This  is a listener that is responsible to read the messages of the queue cardsnotifier
     *   of activeMQ, each message contain a list of cads block generate for cardsManager app.
     *
     * @param cardBlockedList List<Card>
     */
    @JmsListener(destination = "queue.cardsclocked", containerFactory = "myFactory")
    public void listenCardsBlocked(List<Card> cardBlockedList) {

        cardBlokedService.setListCard(cardBlockedList);

        logger.info("ActiveMqListener.listenCardsBlocked:::Received to the listener card blocked list:   <" + cardBlockedList + ">");

    }
}