package com.PONTOON;

public class Card {

    public static String[] suits = { "Clubs", "Spades", "Hearts", "Diamonds" };
    public static byte[] values = { 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13 };

    public String suit;
    public byte value;

    Card(String suit, byte value) {
        this.suit = suit;
        this.value = value;
    }

    public byte getTrueValue() {
        if (this.value == 1) {
            return Main.oneOrEleven();
        } else if (this.value <= 10) {
            return this.value;
        } else {
            return 10;
        }
    }

    //Setters and Getters
    public void setSuit(String suit) {
        this.suit = suit;
    }

    public String getSuit() {
        return suit;
    }

    public void setValue(byte value) {
        this.value = value;
    }

    public byte getValue() {
        return value;
    }
}
