package com.example.consumercardsnotifier.model.service.impl;

import com.example.consumercardsnotifier.model.Card;
import com.example.consumercardsnotifier.model.repository.CardRepository;
import com.example.consumercardsnotifier.model.service.CardBlokedService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.List;

/**
 * Implementation  of the CardBlokedService for the management of card of the application
 *     - Save a list Card
 *     - Update a list Card
 *     - Get a list Card by the field sendTo
 */
@Service
public class CardBlokedServiceImpl implements CardBlokedService {

    @Autowired
    private CardRepository cardBlokerRepository;

    /**
     * - Save a list Card  in the table CARD_BLOCKED_APP_B
     * @param cardBlokedList List<Card>
     */
    @Override
    public void setListCard(List<Card> cardBlokedList) {

        ObjectMapper mapper = new ObjectMapper();
        Iterable<Card> driverlocationsList = mapper.convertValue(cardBlokedList, new TypeReference<Iterable<Card>>() { });
        cardBlokerRepository.saveAll(driverlocationsList);
    }

    /**
     * - Update a list Card Bloked in the table CARD_BLOCKED_APP_B
     * @param cardList List<Card>
     * @return Boolean
     */
    @Override
    public List<Card> upDateList(List<Card> cardList){
        ObjectMapper mapper = new ObjectMapper();
        Iterable<Card> driverlocationsList = mapper.convertValue(cardList, new TypeReference<Iterable<Card>>() { });
        return (List<Card>) cardBlokerRepository.saveAll(driverlocationsList);
    }

    /**
     * - Get a list Card by the field ToSend in the table CARD_BLOCKED_APP_B
     * @param sendTo List<Card>
     * @return Boolean
     */
    @Override
    public List<Card> getListCardsToSend(Boolean sendTo) {
        return cardBlokerRepository.findBySentTo(sendTo);
    }
}