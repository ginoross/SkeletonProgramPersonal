package com.company;

public class Player {
    private String playerName;
    private char symbol;
    private float score;

    public Player(String name) {
        this.playerName = name;
    }

    public String toString(){
        return "Player Name: "+ playerName + ", Symbol: "+symbol+ ", Score: " +score;
    }

    public String getName() {
        return playerName;
    }

    public void setName(String name) {
        this.playerName = name;
    }

    public char getSymbol() {
        return symbol;
    }

    public void setSymbol(char symbol) {
        this.symbol = symbol;
    }

    public float getScore() {
        return score;
    }

    public void setScore(float score) {
        this.score = score;
    }

    public void addScore(){
        score++;
    }

}

