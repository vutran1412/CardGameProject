package com.vu;


// Found the codes for suits unicode on
// https://coderanch.com/t/406524/java/ASCII-Unicode-Card-reprsentation
// Suits are complete sets of constants so
// they are going to be part of an enumerated type
public enum Suit {
    HEARTS("\u2665"),
    SPADES("\u2660"),
    DIAMONDS("\u2666"),
    CLUBS("\u2663");

    private final String suitText;



    // Constructor
    Suit(String suitText) {
        this.suitText = suitText;
    }

    // Displays the unicode symbol
    public String printSuit() {
        return suitText;
    }


}
