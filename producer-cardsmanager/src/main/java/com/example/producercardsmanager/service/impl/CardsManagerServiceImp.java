package com.example.producercardsmanager.service.impl;

import com.example.producercardsmanager.model.Card;
import com.example.producercardsmanager.repository.CardJpaRepository;
import com.example.producercardsmanager.service.CardsManagerService;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import java.util.ArrayList;
import java.util.List;
import java.util.Date;

/**
 * Implementation  of the CardsManagerService for the management of card of the application
 *     - Get All Card
 *     - Get Cards By the field blocked and sent
 *     - Update list cards of the table
 *     - getAllWithExpiryDateTimeBefore
 *     - Block a card by Id
 */
@Service
public class CardsManagerServiceImp implements CardsManagerService {

    @Autowired
    private CardJpaRepository cardBlokerRepository;

    /**
     * - Get All Card of the table CARD_APP_A
     * @return List<Card>
     */
    @Override
    public List<Card> getAllCardService(){

        List<Card> list = new ArrayList<Card>();
        list = (List<Card>) cardBlokerRepository.findAll();
        return list;
    }

    /**
     * - Get Cards By the field blocked and sent of the table CARD_APP_A
     * @param blocked Boolean
     * @param sentTo Boolean
     * @return List<Card>
     */
    @Override
    public List<Card> getCardsByBlockedAndSentService(Boolean blocked, Boolean sentTo) {
        return cardBlokerRepository.findByBlockedAndSentTo(blocked,sentTo);
    }

    /**
     * - Update list cards of the table
     * @param cardList Boolean
     * @return List<Card>
     */
    @Override
    public List<Card> upDateList(List<Card> cardList){

        ObjectMapper mapper = new ObjectMapper();
        Iterable<Card> driverlocationsList = mapper.convertValue(cardList, new TypeReference<Iterable<Card>>() { });
        return cardBlokerRepository.saveAll(driverlocationsList);
    }

    /**
     * - Get all with expiry date time before
     * @param expiryDate Date
     * @return List<Card>
     */
    @Override
    public List<Card>  getAllWithExpiryDateTimeBefore(Date expiryDate) {
        return cardBlokerRepository.findAllWithExpiryDateTimeBefore(expiryDate);
    }

    /**
     * - Block Card by id
     * @param cardNumber Sting
     * @return List<Card>
     */
    @Override
    public List<Card> blockCarById(String cardNumber ){
        Card card = cardBlokerRepository.findByCard(cardNumber);
        card.setBlocked(true);
        cardBlokerRepository.saveAndFlush(card);
        return (List<Card>) cardBlokerRepository.findAll();
    }
}