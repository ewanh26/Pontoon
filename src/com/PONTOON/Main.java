package com.PONTOON;

import java.util.*;

public class Main {

    // DONE: FIVE CARD TRICK, MAYBE IF HAND.GET HAND().SIZE() == 5 {WIN} ?
    // TODO: REPLAYING? "WOULD YOU LIKE TO PLAY AGAIN?"
    // DONE: AI TO PLAY AGAINST. ;)
    // TODO: WHO GOES FIRST?

    private static final Scanner scanner = new Scanner(System.in);
    private static boolean playerTurn;
    private static boolean chooseOne;

    public static byte oneOrEleven() {
        if (playerTurn) {
            System.out.println("Do you want the Ace to be a 1 or an 11?: ");
            if (scanner.nextLine().equals("1")) {
                System.out.println("This Ace will be worth 1");
                return 1;
            } else if (scanner.nextLine().equals("11")) {
                System.out.println("This Ace will be worth 11");
                return 11;
            } else {
                System.out.println("Invalid response");
                return oneOrEleven();
            }
        } else {
            if (chooseOne) return 1;
            else return 11;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        System.out.println("Game start!");

        playerTurn = true;
        Deck deck = new Deck();

        byte score = 0;
        byte opponentScore = 0;

        Hand hand = new Hand();
        Hand opponentHand = new Hand();

        deck.shuffle();

        game_loop : while (true) {
            String input = scanner.next();

            while (playerTurn) {
                switch (input) {
                    case "stop" -> {
                        break game_loop;
                    }

                    case "twist" -> {
                        Card drawnCard = deck.drawCard();
                        hand.addCardToHand(drawnCard);
                        System.out.println("Your hand:");
                        hand.print();
                        System.out.println();

                        score += drawnCard.getTrueValue();
                        if (score > 21) {
                            System.out.println("Bust!");
                            score = 0;
                            playerTurn = false;
                            break;
                        } else if (hand.get().size() == 5) {
                            System.out.println("5 card trick! You win!");
                            break game_loop;
                        }
                        continue game_loop;
                    }

                    case "stick" -> {
                        System.out.println("Your score: "+ score+ "\n");
                        if (score == 21) {
                            System.out.println("21! You win");
                            break game_loop;
                        }
                        playerTurn = false;
                    }

                    default -> {
                        System.out.println("Invalid response");
                        continue game_loop;
                    }
                }
            }

            while (!playerTurn) {
                if (opponentScore < 15) {
                    //Waits a second for playability
                    Thread.sleep(1000);
                    //twist
                    chooseOne = opponentScore + 11 > 21;

                    Card drawnCard = deck.drawCard();
                    opponentHand.addCardToHand(drawnCard);
                    System.out.println("The opponent has twisted.");

                    opponentScore += drawnCard.getTrueValue();
                    if (opponentScore > 21) {
                        System.out.println("The opponent is bust! You win!\nTheir hand was: ");
                        opponentHand.print();
                        break game_loop;
                    } else if (opponentHand.get().size() == 5) {
                        System.out.println("Opponent has a 5 card trick! You lose");
                        break game_loop;
                    }
                } else {
                    //stick
                    System.out.println("The opponent sticks at "+ opponentScore);
                    if (opponentScore == 21) {
                        System.out.println("Opponent has 21! You lose");
                        break game_loop;
                    }
                    if (score > opponentScore) {
                        System.out.println("Higher score! You win!");
                    } else if (score == opponentScore) {
                        byte royals = 0;
                        byte opponentRoyals = 0;
                        for (Card card : hand.get()) {
                            if (card.value > 10) royals++;
                        }
                        for (Card card : opponentHand.get()) {
                            if (card.value > 10) opponentRoyals++;
                        }
                        if (royals > opponentRoyals) {
                            System.out.println("Same score, but you win with more royal cards!");
                        } else if (royals == opponentRoyals) {
                            System.out.println("Draw! Same royals, same score!");
                        } else {
                            System.out.println("Same score, but opponent beat you with more royals!");
                        }
                    } else {
                        System.out.println("Opponent beat you with a higher score.");
                    }

                    Thread.sleep(1000);
                    System.out.println("The opponent's hand was: ");
                    opponentHand.print();

                    break game_loop;
                }
            }
        }

        Thread.sleep(3000);
    }

}
