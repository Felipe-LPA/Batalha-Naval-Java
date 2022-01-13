package com.projetoFinalModulo1.navalBattle.auxClass;

abstract public class  Player {
    public Board board = new Board();
    protected String name;
    private int score;

    public int getScore() {
        return score;
    }

    public void setScore() {
        this.score++;
    }


    public String getName() {
        return name;
    }
}
