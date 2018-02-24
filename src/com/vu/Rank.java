package com.vu;


// The card ranks are going to be constants
public enum Rank {
    TWO(2, "2"), FOUR(4, "4"), FIVE(5, "5"), SIX(6, "6"),
    SEVEN(7, "7"), EIGHT(8, "8"), NINE(9, "9"), TEN(10, "10"),
    ACE(11, "A");


    public final int rankValue;
    public final String rankString;
    // Constructor, the only thing  we need here is the integer value
    // and the string to represent the object
    Rank(int rankValue, String rankString) {
        this.rankValue = rankValue;
        this.rankString = rankString;
    }


    public int getRankValue() {
        return rankValue;
    }

    public String printRank() {
        return rankString;
    }

}
