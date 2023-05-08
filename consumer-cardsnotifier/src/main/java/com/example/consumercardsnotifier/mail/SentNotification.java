package com.example.consumercardsnotifier.mail;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.MailParseException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.messaging.MessagingException;
import org.springframework.stereotype.Component;
import javax.mail.internet.MimeMessage;

/**
 * - The purpose component is  content different methods to to send notification.
 * - In this demo there is just a method to send a mail but could be more.
 *
 * - the security option in gmail should be configure to send mail from your mail
 *     + https://myaccount.google.com/lesssecureapps
 *     + https://accounts.google.com/b/0/DisplayUnlockCaptcha
 */
@Component
public class SentNotification {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    private JavaMailSender javaMailSender;

    @Value("${mail.mail-from}")
    private String mailFrom;

    @Value("${mail.mail-to}")
    private String mailTo;

    @Value("${mail.mail-subject}")
    private String mailSubjec;

    @Value("${mail.mail-text}")
    private String mailText;

    @Value("${mail.mail-dear}")
    private String mailDear;

    @Value("${mail.mail-text-two}")
    private String mailTextTwo;

    /**
     * - This  method  send a mail as a notification that any cards had blocked
     * - The security option in gmail should be configure to send mail from your mail
     *     + https://myaccount.google.com/lesssecureapps
     *     + https://accounts.google.com/b/0/DisplayUnlockCaptcha
     *
     * @param pathFile String
     */
    public void sendMail(String pathFile) {

        logger.info("#### SentNotification.sendMail:::Sending mail ");

        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();
        simpleMailMessage.setFrom(mailFrom);
        simpleMailMessage.setTo(mailTo);
        simpleMailMessage.setSubject(mailSubjec);
        simpleMailMessage.setText(mailText);

        MimeMessage message = javaMailSender.createMimeMessage();

        try{
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(simpleMailMessage.getFrom());
            helper.setTo(simpleMailMessage.getTo());
            helper.setSubject(simpleMailMessage.getSubject());
            helper.setText(String.format(
                    simpleMailMessage.getText(), mailDear, mailTextTwo));

            FileSystemResource file = new FileSystemResource(pathFile);
            helper.addAttachment(file.getFilename(), file);

            javaMailSender.send(message);

        }catch(MessagingException e) {
            logger.info("#### Jump the Exception "+ e.getFailedMessage());
        }catch(javax.mail.MessagingException e) {
            logger.info("####  Exception " + e.getMessage());
        }catch(Exception e) {
            logger.info("####  Exception "+ e.getMessage());
        }

        logger.info("######## SentNotification.sendMail:::Mail sent ");
    }
}