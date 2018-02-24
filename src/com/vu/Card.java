package com.vu;

import javax.print.DocFlavor;

public class Card {

    // The card object is built using the rank and suit objects
    public Suit suit;
    public Rank rank;



    // Constructor
    public Card(Rank rank, Suit suit) {
        this.rank = rank;
        this.suit = suit;

    }

    // Getters and setters
    public String getSuit() {
        return suit.printSuit();
    }

    public int getRank() {
        return rank.getRankValue();
    }


    Suit getSuitEnum() {
        return this.suit;
    }

    // To string
    public String toString() {
         String str = "";
         str += rank.printRank() + " of " + suit.printSuit() + "";
         return str;
    }
}
