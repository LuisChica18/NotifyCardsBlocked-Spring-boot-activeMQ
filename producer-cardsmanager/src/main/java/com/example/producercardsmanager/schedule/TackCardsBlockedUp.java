package com.example.producercardsmanager.schedule;

import com.example.producercardsmanager.controller.ControllerCardsManager;
import com.example.producercardsmanager.model.Card;
import com.example.producercardsmanager.service.ActiveMqService;
import com.example.producercardsmanager.service.CardsManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.util.Date;
import java.util.List;

/**
 * - The follow tack is going to find all the cars blocked in the table CARD_APP_A and will send to the activeMQ
 *   and the consumer of the app CardsBlockedApp will reed to active MQ and save this list of cards blocked
 *   in the table CARD_BLOCKED_APP_B of the app CardsBlockedApp.
 *
 */
@Component
public class TackCardsBlockedUp {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ActiveMqService sctiveMqService;

    @Autowired
    CardsManagerService cardService;

    @Autowired
    ControllerCardsManager controllerCardsManager;


    /**
     * - Check the  Expiry Data of the cards of CARD_APP_A and change the field blocked to true
     *   if  ExpiryData < current data
     */
    @Scheduled(cron = "*/10 * * * * *", zone = "America/Mexico_City")
    private void expiredDateBloked(){

        List<Card> cardExpiredBlocked =cardService.getAllWithExpiryDateTimeBefore(new Date());
        if(cardExpiredBlocked.isEmpty())
            return;

        logger.info("#### Scheduled.Track:expiredDateBlocked():::Check the table CARD_APP_A and block the cards that expired Date" );

        //upDateList field blocked
        cardExpiredBlocked.forEach(f -> f.setBlocked(true));
        cardService.upDateList(cardExpiredBlocked);
    }

    /**
     * - Check the table CARD_APP_A and Send to the activeMQ the cards block list, it will change the state of
     *   the field SentTo to true.
     */
    @Scheduled(cron = "*/50 * * * * *", zone = "America/Mexico_City")
    private void sendBloked(){

        List<Card> cardBlokedSend =cardService.getCardsByBlockedAndSentService(true,false);
        if (cardBlokedSend.isEmpty())
            return;

        logger.info("#### Scheduled.Track:sendBlocked():::Check the table CARD_APP_A and Send to the activeMQ the cards block list" );

        try{
            //Sent to CardsBlockedApp
            sctiveMqService.sendCardsBlockedApp(cardBlokedSend);

            //upDateList field setSentTo
            cardBlokedSend.forEach(f -> f.setSentTo(true));
            cardService.upDateList(cardBlokedSend);

        }catch (Exception e){
            logger.info(e.getMessage());
        }
    }
}