package com.vu;


import java.util.ArrayList;
import java.util.Collections;


public class Deck {
    // Deck is made of ArrayList of Card objects
    private ArrayList<Card> deck = new ArrayList<>();
    // Deck constructor
    Deck() {
        // Nestled loops to put together the card objects
        // Outside loop is for suit
        for (Suit suit : Suit.values()) {
            // Inside loop is for rank
            for (Rank rank : Rank.values()) {
                // As long as the Card object is not an Ace of Spades
                // It's better to take care of this problem now than later
                if (!(rank == Rank.ACE && suit == Suit.SPADES)) {
                    // then create the the card object
                    Card card = new Card(rank, suit);
                    // add to the ArrayList
                    this.deck.add(card);
                }
            }
        }

    }

    // void method to shuffle the deck
    public void shuffle() {
        Collections.shuffle(this.deck);
    }

    // Just like a real deck the top card is removed to deal
    public Card dealCard() {
        Card topCard = deck.get(0);
        deck.remove(0);
        return topCard;
    }

    @Override
    public String toString() {
        return "Deck: " +
                "deck=" + deck +
                '}';
    }
}

