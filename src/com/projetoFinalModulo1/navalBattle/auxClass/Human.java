package com.projetoFinalModulo1.navalBattle.auxClass;

public class Human extends Player{
    public int checkNumber(String row){
        return this.board.getNumber(row);
    }
    public void setName(String name){
        super.name = name;
    }

}
