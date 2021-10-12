package com.taco.services;

import com.taco.repository.CardRepository;
import com.taco.entities.Card;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class CardServices {

    private final CardRepository cardRepository;

    //update card
    public Card updateCard(Card card){
        return cardRepository.save(card);
    }
}
