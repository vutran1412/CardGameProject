package com.vu;

public class Agram {

    // Game structure based on
    // https://stackoverflow.com/questions/4870165/player-order-in-card-game

        // The game model stores an array of player names
        // and array of Player objects
        private String[] playerNames;
        private Player[] players;
        private int playerTurnIndex = 0;
        private int winnerIndex = 0;

        // Constructor accepts an array of player names
        // Assigns the string values to specific player object and
        // store them in array
        Agram(String[] playerNames) {
            this.playerNames = playerNames;
            players = new Player[playerNames.length];
            for (int i = 0; i < playerNames.length; i++) {
                players[i] = new Player(playerNames[i]);
            }
        }

        // Getters to return the specific player index in the array
        public int getPlayerTurnIndex(){
            return playerTurnIndex;
        }

        Player getCurrentPlayer(){
            return players[playerTurnIndex];
        }

        public String[] getPlayerNames(){
            return playerNames;
        }

        // void method set the next turn in the game
        // loops through the array and check to see if its
        // the current player's turn
        void advancePlay() {
            playerTurnIndex++;
            playerTurnIndex %= players.length;
            for (Player player: players) {
                if (player != getCurrentPlayer()) {
                    player.setMyTurn(false);
                } else {
                    player.setMyTurn(true);
                }
            }
        }

        //re-initializes the turn order. Argument is the player array index.
        //remember to getPlayerTurnIndex() before calling this.
        void startNewRound(int playerIndex){
            //reset all the players except the new starting player
            for (int i = 0; i < players.length; i++) {
                if (i == playerIndex) {
                    players[i].setMyTurn(true);
                } else {
                    players[i].setMyTurn(false);
                }
            }
            // and initialize this index in the object's variable
            playerTurnIndex = playerIndex;
        }

        // More getters and setters to set the current player as the winning player
        int getPlayerCount(){
            return players.length;
        }

        Player getPlayer(int i){
            return players[i];
        }

        int getWinnerIndex(){
            return this.winnerIndex;
        }

        Player getWinningPlayer(){
            return getPlayer(getWinnerIndex());
        }

        boolean isCurrentPlayerWinning(){
            return this.playerTurnIndex == this.winnerIndex;
        }

        void setWinning(){
            this.winnerIndex = this.playerTurnIndex;
        }



    }

