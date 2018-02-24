package com.vu;

import java.util.LinkedList;

// The trick object is going to store card objects in an LinkedList
public class Trick {

    // The LinkedList to represent the cards in play
    private LinkedList<Card> trickPile = new LinkedList<>();
    // The suit object is important when comparing the suits of the cards
    private Suit suit;
    // The highest card value to compare the cards within the trick
    private int highestCard;
    // For display purposes
    private String highestCardString;

    // Trick constructor accepts the first Card object
    Trick(Card leadCard) {
        this.trickPile.add(leadCard);
        // The suit of the lead card is important
        this.suit = leadCard.getSuitEnum();
        // The highest value card in the trick that matches the suit of the lead card
        // will be the winner
        this.highestCard = leadCard.getRank();
        // Display
        this.highestCardString = leadCard.toString();

    }

    // Getters and setters, auto generated
    public Suit getSuit() {
        return suit;
    }

    public void setSuit(Suit suit) {
        this.suit = suit;
    }

    public int getHighestCard() {
        return highestCard;
    }

    public LinkedList<Card> getTrickPile() {
        return trickPile;
    }

    public void setTrickPile(LinkedList<Card> trickPile) {
        this.trickPile = trickPile;
    }

    public void setHighestCard(int highestCard) {
        this.highestCard = highestCard;
    }

    public String getHighestCardString() {
        return highestCardString;
    }

    public void setHighestCardString(String highestCardString) {
        this.highestCardString = highestCardString;
    }

    // method to test whether the card in the player's hand matches the suit of the lead card
    boolean validCard(Card newCard, Player player) {
        boolean valid = true;
        // Checks the suit of the player's card
        Suit playedCardSuit = newCard.getSuitEnum();
        // If the suit doesn't match any of the suits of the cards in the
        // player's hand the card, is not valid to play
        if (playedCardSuit != this.suit) {
            for (Card card : player.getHand()) {
                if (card != newCard && card.getSuitEnum() == this.suit) {
                    valid = false;
                }
            }
        }
        return valid;
    }

    // If the player plays a card of the same suit as the leading card in the trick
    // and with a higher value, the current player is winning
    boolean winningCard(Card card) {
        boolean winning = false;
        if (card.getSuitEnum() == this.suit) {
            if (card.getRank() > this.highestCard) {
                winning = true;
            }
        }
        return winning;
    }

    // if the card is valid add to the pile
    boolean addToTrick(Card playedCard) {
        boolean winning = false;
        this.trickPile.add(playedCard);
        if (playedCard.getSuitEnum() == this.suit) {
            if (playedCard.getRank() > this.highestCard) {
                this.highestCard = playedCard.getRank();
                winning = true;
            }
        }
        return winning;
    }

    @Override
    public String toString() {
        return "Trick{" +
                "trickPile=" + trickPile +
                '}';
    }
}
