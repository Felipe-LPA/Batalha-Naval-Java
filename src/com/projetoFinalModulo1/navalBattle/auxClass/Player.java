package com.projetoFinalModulo1.navalBattle.auxClass;

abstract public class  Player {
//    protected String type;
    public Board board = new Board();
    protected String name;
    private int score;

    public String getName() {
        return name;
    }
}
