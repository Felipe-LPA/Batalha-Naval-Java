package com.projetoFinalModulo1.navalBattle.auxClass;

import java.util.Random;

public class Bot extends Player{

    public int doTurn(int column, int row){
        return this.board.thereIsShip(column, row);
    }

    public void setName(){
        Random random = new Random();
        super.name = "bot" + random.nextInt(99);
    }
}
