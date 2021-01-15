package com.PONTOON;

import java.util.ArrayList;
import java.util.Collections;

public class Deck implements CardCollection {

    private ArrayList<Card> deck;

    public Deck() {
        deck = new ArrayList<>();

        for (String suit : Card.suits) {
            for (byte value : Card.values) {
                Card card = new Card(suit, value);
                deck.add(card);
            }
        }
    }
    public void print() {
        for (Card card : deck) {
            switch (card.value) {
                case 1 -> System.out.println("Ace of " + card.suit);
                case 11 -> System.out.println("Jack of " + card.suit);
                case 12 -> System.out.println("Queen of " + card.suit);
                case 13 -> System.out.println("King of " + card.suit);
                default -> System.out.println(card.value + " of " + card.suit);
            }
        }
    }

    public void shuffle() {
        Collections.shuffle(deck);
    }

    public Card drawCard() {
        Card drawnCard = deck.get(0);
        deck.remove(0);
        return drawnCard;
    }

}
