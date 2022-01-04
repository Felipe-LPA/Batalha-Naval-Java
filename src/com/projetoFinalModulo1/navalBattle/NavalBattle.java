package com.projetoFinalModulo1.navalBattle;

import com.projetoFinalModulo1.navalBattle.auxClass.Bot;
import com.projetoFinalModulo1.navalBattle.auxClass.Human;

import java.util.Random;
import java.util.Scanner;

public class NavalBattle {


public static void start(){
    Scanner sc = new Scanner(System.in);

    Bot bot = new Bot();
    bot.setName();
    bot.board.setEmptyPositions();
    bot.board.setShips(true);
//    bot.board.printPosition();

    Human human = new Human();
    System.out.print("Digite seu nome: ");
    String name = sc.nextLine();
    human.setName(name);
//    System.out.println(human.getName());
    boolean invalidOptSetShips = true;
    boolean setShipsAuto = true;
    while(invalidOptSetShips){
        System.out.print("Escolha uma das opções para espalhar seus navios pelo tabuleiro (1) para automático ou (2) manual: ");
        int opt = Integer.parseInt(sc.nextLine());
        if(opt == 1 || opt == 2){
            invalidOptSetShips = false;
            if (opt == 2){
                setShipsAuto = false;
            }
        }
    }
    human.board.setEmptyPositions();
    human.board.setShips(setShipsAuto);
    human.board.printPosition();
}
    private boolean verifyWinner() {
        return false;
    }
}
