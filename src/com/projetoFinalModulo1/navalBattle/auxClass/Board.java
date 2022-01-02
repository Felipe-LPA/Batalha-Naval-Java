package com.projetoFinalModulo1.navalBattle.auxClass;

import java.util.Arrays;

public class Board {
    private final String[][] POSITIONS = new String[10][10];
    private int ships = 10;

    public void updatePositions(int column, char rowLetter, String mark) {
        int row = this.getNumber(rowLetter);
            POSITIONS[row][column] = mark;
    }
    public void printPosition() {
        System.out.printf("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |%n");
        for (int i = 0; i < POSITIONS.length; i++) {
            System.out.printf("| %s | %s |%n" ,this.getLetter(i), String.join(" | ", POSITIONS[i]));
        }
    }
//    fazer a inclusão dos navios com o metodo updatePositions
//    public void setShips(int column, char rowLetter) {
//        this.updatePositions(column, rowLetter, "n");
//    }
//    Movido para NavalBattle
//    public boolean verifyWinner() {
//        return false;
//    }

    private String getLetter(int index){
        switch (index){
            case 0:
                return "A";
            case 1:
                return "B";
            case 2:
                return "C";
            case 3:
                return "D";
            case 4:
                return "E";
            case 5:
                return "F";
            case 6:
                return "G";
            case 7:
                return "H";
            case 8:
                return "I";
            case 9:
                return "J";
            default:
                return " ";
        }
    }

    private int getNumber(char index){
        switch (index){
            case 'A':
            case 'a':
                return 0;
            case 'B':
            case 'b':
                return 1;
            case 'C':
            case 'c':
                return 2;
            case 'D':
            case 'd':
                return 3;
            case 'E':
            case 'e':
                return 4;
            case 'F':
            case 'f':
                return 5;
            case 'G':
            case 'g':
                return 6;
            case 'H':
            case 'h':
                return 7;
            case 'I':
            case 'i':
                return 8;
            case 'J':
            case 'j':
                return 9;
            default:
                return 99;
        }
    }

    public void setInitialPositions(){
        for (String[] position : POSITIONS) {
            Arrays.fill(position, " ");
        }
    }
}

