package com.pranitpatil.kalah.database.entity;

public class Game implements Cloneable{

    private int id;
    private boolean gameOver = false;
    private GameData gameData;
    private Player currentChance = null;

    public Game(int id, int initialPitCount) {
        this.id = id;
        this.gameData = new GameData(initialPitCount);
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public boolean isGameOver() {
        return gameOver;
    }

    public void setGameOver(boolean gameOver) {
        this.gameOver = gameOver;
    }

    public GameData getGameData() {
        return gameData;
    }

    public void setGameData(GameData gameData) {
        this.gameData = gameData;
    }

    public Player getCurrentChance() {
        return currentChance;
    }

    public void setCurrentChance(Player currentChance) {
        this.currentChance = currentChance;
    }

    @Override
    protected Object clone() throws CloneNotSupportedException {
        return super.clone();
    }
}
