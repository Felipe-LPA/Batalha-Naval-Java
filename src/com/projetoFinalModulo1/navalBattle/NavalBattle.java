package com.projetoFinalModulo1.navalBattle;

import com.projetoFinalModulo1.navalBattle.auxClass.Board;

public class NavalBattle {


public static void start(){
    Board board = new Board();
    board.setInitialPositions();
    board.printPosition();
    board.updatePositions(1, 'c', "X");
    board.printPosition();
}
    private boolean verifyWinner() {
        return false;
    }
}
