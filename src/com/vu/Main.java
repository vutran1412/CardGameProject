package com.vu;

import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;
import java.util.concurrent.ThreadLocalRandom;


// Agram game Project
// Vu Tran
//
public class Main {
    // Scanner class to accept user input
    private static Scanner scanner = new Scanner(System.in);
    private static int numberOfPlayers;



    public static void main(String[] args) {
        // write your code here
        // Introduction and rules got via wikipedia
        System.out.println("\t\t\t\t\tAgram");
        System.out.println();
        System.out.println("The game can be played with 2 to 5 players");
        System.out.println("There are 35 cards in the deck");
        System.out.println("Agram uses Ace, 10, 9, 8, 7, 6, 5, 4, 2 of each suit");
        System.out.println("The Ace of Spades is the chief and will be removed");
        System.out.println("Aces are high!");
        System.out.println("Each player will be dealt six cards");
        System.out.println("Highest card matching the suit of the first card in the trick wins!");
        System.out.println("Whoever wins the trick is the leader");
        System.out.println("Whoever wins the sixth round wins the game");

        // while loop to accept player names
        while(true) {
            System.out.println("How many players?");
            numberOfPlayers = scanner.nextInt();
            // Data entry validation, the game needs at least 2 and no more than 5
            if (numberOfPlayers > 1 && numberOfPlayers < 6) {
                break;
            }
            System.out.println("Agram can only be played with 2 to 5 players only!");
        }

        boolean accept;
        String [] playerNames;
        // do while loop to get the player names, test the accept condition after
        // getting the data we need
        do {
            // Create an Array to store the names as strings
            playerNames = new String[numberOfPlayers];
            // For loop to store the names in the array
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.println("Enter the name of player " + (i + 1) + ": ");
                String playerName = scanner.next();
                if (playerName.isEmpty()) {
                    playerNames[i] = " Player " + (i + 1);
                } else {
                    playerNames[i] = playerName;
                }
            }
            // Confirm with the player, all the info was entered correctly
            // If player accepts, break out of the loop
            System.out.println("Players");
            for (int i = 0; i < numberOfPlayers; i++) {
                System.out.printf("%s\n", playerNames[i]);
            }
            System.out.println();
            System.out.printf("Is this correct? (Y/N)");
            String ok = scanner.next();
            System.out.println();
            if (ok.toLowerCase().equals("y")) {
                accept = true;
            } else {
                accept = false;
            }
        } while (!accept);

        // Create the agram game model object
        Agram agram = new Agram(playerNames);

        // Create tbe deck object
        Deck deck = new Deck();

        // We need to generate a random int to choose who the dealer is
        int playerOne = ThreadLocalRandom.current().nextInt(0, numberOfPlayers);

        // New round from the playerOne index, where ever that is
        agram.startNewRound(playerOne);

        // Shuffle
        deck.shuffle();
        System.out.println();
        System.out.println(playerNames[playerOne] + " is dealer");
        System.out.println();
        // Deals to the next player
        agram.advancePlay();
        // Since all the players are dealt three card at a time
        // the outside loop will loop twice, and the inner loop
        // will loop thrice
        for (int c = 0; c < (numberOfPlayers * 2); c++) {
            System.out.println("Deal three cards to " + agram.getCurrentPlayer().toString());
            for (int i = 0; i < 3; i++) {
                // creates and deals three cards at a time
                Card card = deck.dealCard();
                // adds all the cards to the player's hands
                agram.getCurrentPlayer().addCard(card);
            }
            // the deal is treated like a turn
            // so we advance play
            agram.advancePlay();
        }
        System.out.println();

        // Assert to verify that every player will start with 6 cards
        for (int x = 0; x <= numberOfPlayers; x++) {
            assert (agram.getPlayer(x).getHand().size() == 6) : agram.getPlayer(x).getName();
        }

        // Continue
        System.out.println("Are you ready to play? (Y/N)");
        String play = scanner.next();

        // Starts the new round,
        agram.startNewRound(playerOne);
        agram.advancePlay();
        System.out.println();

        // Game will last 6 rounds
        for (int round = 1; round < 6; round++) {

            // Creates a trick pile for the game
            Trick pile = new Trick(playerOne(agram));

            System.out.println("Are you ready for next player?");
            String nextPlay = scanner.next();

            for (int p = 1; p < numberOfPlayers; p++) {
                // Next player's turn
                agram.advancePlay();

                // Computer player automatically selects a card based on game logic
                boolean winner = cpuPlayer(agram, pile);
                if (winner) {
                    agram.setWinning();
                    System.out.println("You are winning");
                } else {
                    System.out.println("You are not winning");
                }
                // Continue
                System.out.println("Continue? (Y/N)");
                String next = scanner.next();
            }
            // void method to clear the screen by
            // looping 20 times and println()
            clear();
            // Game screen
            System.out.println("The trick");
            System.out.println();
            System.out.println(pile.toString());
            System.out.println(agram.getWinningPlayer().getName() + " played the highest card");
            if (round < 5) {
                System.out.println(agram.getWinningPlayer().getName() + " wins the trick and is the new leader");
                // The winner of the trick will start the next round
                agram.startNewRound(agram.getWinnerIndex());
                System.out.println("Continue? (Y/N)");
                String next = scanner.next();
            } else {
                // Winner of the final round will be declared the winner
                System.out.println(agram.getWinningPlayer().getName() + " is the winner!");
            }
        }
        // Closes and exits the program
        scanner.close();
        System.exit(0);


    }

    // The first round the player doesn't have a trick yet
    private static Card playerOne(Agram agram) {
        System.out.println("\t\t\t\tNewRound");
        System.out.println();
        System.out.println(agram.getCurrentPlayer().toString() + "'s turn");
        agram.getCurrentPlayer();

        int index = 0;
        // Creates a menu like array to make user selection of cards easier
        Card[] cards = new Card[agram.getCurrentPlayer().getHand().size()];
        for (Card card: agram.getCurrentPlayer().getHand()) {
            cards[index] = card;
            index++;
        }

        System.out.println("Current trick");
        System.out.println();
        System.out.println("Select a lead card for this trick");
        System.out.println();
        // Displays the cards horizontally, just like a real game
        System.out.println("***" + agram.getCurrentPlayer().getName() + "'s cards");
        System.out.println(agram.getCurrentPlayer().getHand().toString());
        // Loops over the array and displays the menu on each
        // separate line with the corresponding card integer selections
        for (int i = 0; i < cards.length; i++) {
            System.out.printf("%1d: %s\n", i + 1, cards[i].toString());
        }

        System.out.println();

        System.out.println("Select a card (1 to " + agram.getCurrentPlayer().getHand().size() + "):");
        int selectedCard;
        boolean validCard = false;

        do {
            // take user input
            selectedCard = scanner.nextInt() - 1;
            // Validation input to make sure the index is not negative or larger than current player's hand
            if (selectedCard >= 0 &&  selectedCard < agram.getCurrentPlayer().getHand().size()) {
                validCard = true;

            } else {
                System.out.println("Number not valid, enter a valid number");
            }
        } while (!validCard);
        // return the card value to the trick pile, removes the card from hand
        return agram.getCurrentPlayer().getHand().remove(selectedCard);
    }

    // CPU player accepts the game structure and the trick
    private static boolean cpuPlayer(Agram agram, Trick trick) {
        System.out.println("Current trick");
        // displays the current trick
        System.out.println(trick.toString());
        System.out.println();
        // Creates the hand for the computer player
        System.out.println("Begin turn for " + agram.getCurrentPlayer().getName());
        Card[] currentHand = new Card[agram.getCurrentPlayer().getHand().size()];


        // CPU game logic ****************************************************
        // Not 100% AI, if the computer wins the player will still play the lead card in the trick at the start of every round
        // But overall the game logic works, the computer is making the best possible play every turn
        // I've lost to the computer a few times.
        // Will probably continue working on this
        int cardIndex = 0;
        Card winningCard;
        boolean cardToAddToTrick = false;
        // Stores ArrayList of cards inside an array
        for (Card card : agram.getCurrentPlayer().getHand()) {
            currentHand[cardIndex] = card;
            cardIndex++;
        }
        // Loops over the hand
        for (int i = 0; i < currentHand.length; i++) {
            // if the card in hand is the same as the leading trick card
            // And the value is grater than the trick card
            if (trick.validCard(currentHand[i], agram.getCurrentPlayer())
                    && trick.winningCard(currentHand[i])) {
                // Output to verify the outcome
                System.out.printf("%1d: %-16s is a winning card ", i + 1, currentHand[i].toString());
                // assigns the card in the hand as a winning card
                winningCard = currentHand[i];
                // moves the card from hand into the trick pile
                cardToAddToTrick = trick.addToTrick(agram.getCurrentPlayer().moveFrom(winningCard));
            } else if (trick.validCard(currentHand[i], agram.getCurrentPlayer())) {
                System.out.printf("%1d: %s\n", i + 1, currentHand[i].toString());
            } else {
                System.out.printf("%-16s Not a valid play\n", currentHand[i].toString());

                }


            }
            return cardToAddToTrick;
    }


    private static void clear() {
        for (int i = 0; i < 20; i++) {
            System.out.println();
        }
    }
}
