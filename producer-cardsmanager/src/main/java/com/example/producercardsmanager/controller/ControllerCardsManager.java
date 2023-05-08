package com.example.producercardsmanager.controller;

import com.example.producercardsmanager.model.Card;
import com.example.producercardsmanager.service.ActiveMqService;
import com.example.producercardsmanager.service.CardsManagerService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * Controller that redirects the requests received to the different services depending on the functionality required
 *
 *  - Go to the index page
 *  - Block by Id
 *
 */
@Controller
public class ControllerCardsManager {

    private Logger logger = LoggerFactory.getLogger(this.getClass());

    @Autowired
    ActiveMqService activeMqService;

    @Autowired
    CardsManagerService cardBlokedService;

    /**
     * - Go to the index page
     *
     * @param model Model
     * @return String
     */
    @GetMapping("/")
    public String index(Model model){

        logger.info("#### @GetMapping::: Load the web site cards_blocker.html");
        List<Card> cardBlokedList =cardBlokedService.getAllCardService();
        model.addAttribute("cardBlock", new Card());
        model.addAttribute("cardBlockList", cardBlokedList);
        return "cards_blocker";
    }

    /**
     * - Block by Id
     * @param cardNumberSelect String
     * @param model Model
     * @return String
     */
    @PostMapping("/block")
    public String blockById(@RequestParam String cardNumberSelect, Model model) {

        logger.info("#### @PostMapping::: It´s selected the card "+cardNumberSelect+" to be blocked by hand ");
        cardBlokedService.blockCarById(cardNumberSelect);
        List<Card> cardBlokedList =cardBlokedService.getAllCardService();

        model.addAttribute("cardBlock", new Card());
        model.addAttribute("cardBlockList", cardBlokedList);
        return "redirect:/";
    }
}