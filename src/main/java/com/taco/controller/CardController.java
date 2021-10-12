package com.taco.controller;

import com.taco.entities.Card;
import com.taco.services.CardServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cards")
public class CardController {

    @Autowired
    private CardServices cardServices;

    @PutMapping
    public Card updateCard(@RequestBody Card card){
        return cardServices.updateCard(card);
    }
}
