package com.PONTOON;

import java.util.ArrayList;
import java.util.Scanner;

public class Hand {

    private ArrayList<Card> hand;
    private static final Scanner scanner = new Scanner(System.in);

    public Hand() {
        hand = new ArrayList<>();
    }

    public void printHand() {
        for (Card card : hand) {
            switch (card.value) {
                case 1 -> System.out.println("Ace of " + card.suit);
                case 11 -> System.out.println("Jack of " + card.suit);
                case 12 -> System.out.println("Queen of " + card.suit);
                case 13 -> System.out.println("King of " + card.suit);
                default -> System.out.println(card.value + " of " + card.suit);
            }
        }
    }

    public void addCardToHand(Card card) {
        hand.add(card);
    }

    public ArrayList<Card> getHand() {
        return hand;
    }

    public void setHand(ArrayList<Card> hand) {
        this.hand = hand;
    }
}
