package com.example.consumercardsnotifier.schedule;

import com.example.consumercardsnotifier.mail.BuildFile;
import com.example.consumercardsnotifier.mail.SentNotification;
import com.example.consumercardsnotifier.model.Card;
import com.example.consumercardsnotifier.model.service.CardBlokedService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.util.List;

/**
 * - The follow component is a tack with the functionality to send notifications, in this demo there is
 *   just a method to send an excel by mail to a inspector role but could be more scheduled shipments.
 */
@Component
public class TacksSendNotification {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    SentNotification sentNotification;

    @Autowired
    BuildFile buildFile;

    @Autowired
    CardBlokedService cardBlokedService;

    /**
     * - This task Sent a notification to the inspector by mail with a excel of list blocked.
     * - Build file excel, Sent notification, update the field setSentTo.
     * - In this demo the tack will execute every 10 second, in a real situation probably  it will be another.
     */
    @Scheduled(cron = "*/10 * * * * *")
    private void sendNotificationMail(){

        List<Card> cardsSentTo =cardBlokedService.getListCardsToSend(false);
        if(cardsSentTo.isEmpty())
            return;

        logger.info("#### @Scheduled.sendNotificationMail:::Sent a notification by mail with a excel of list blocked.");

        //Build file excel
        String folder ="src\\main\\resources\\filessend\\file";
        String pathFile = buildFile.excelWriter(cardsSentTo, folder);

        //Sent notification
        sentNotification.sendMail(pathFile);

        //upDateList field setSentTo
        cardsSentTo.forEach(f -> f.setSentTo(true));
        cardBlokedService.upDateList(cardsSentTo);
    }
}