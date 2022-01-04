package com.projetoFinalModulo1.navalBattle.auxClass;

import java.util.*;

public class Board {
    private final String[][] POSITIONS = new String[10][10];
    private int ships = 10;
    private final String LETTERS = "ABCDEFGHIJ";

//    public void updatePositions(int column, char rowLetter, String mark) {
//        int row = this.getNumber(rowLetter);
//            this.POSITIONS[row][column] = mark;
//    }
    public void updatePositions(int column, int row, String mark) {
        this.POSITIONS[row][column] = mark;
    }
    public void printPosition() {
        System.out.printf("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |%n");
        for (int i = 0; i < this.POSITIONS.length; i++) {
            System.out.printf("| %s | %s |%n" ,this.getLetter(i), String.join(" | ", this.POSITIONS[i]));
        }
    }

    public boolean isValidPosition(int column, int row) {
        return Objects.equals(this.POSITIONS[row][column], " ");
    }

    //    fazer a inclusão dos navios com o metodo updatePositions
    public void setShips(boolean isAuto) {
        int i = 0;
//        int column;
//        int row;
        while (i < this.ships) {
                Random random = new Random();
                int column = random.nextInt(10);
                int row = random.nextInt(10);
            if(!isAuto){
                Scanner sc = new Scanner(System.in);
                this.printPosition();
                System.out.printf("Para posicionar o navio %d escolha uma das opções: %nInforme uma posição ainda não preenchida em formato válido(Ex: A2, b4, c7...)%n Digite r para random%nopç: ", i+1);
                String position = sc.nextLine();
                if(!Objects.equals(position, "r") && !Objects.equals(position, "R")){
                    String[] splitedPosition = position.split("");
                    row = this.getNumber(splitedPosition[0]);
                    column =  Integer.parseInt(splitedPosition[1]);
                }
            }
//            System.out.printf("row: %d, column: %d  - %s%n", row, column, this.isValidPosition(column, row));
            if (row != 99 && this.isValidPosition( column, row)) {
                this.updatePositions(column, row, "N");
                i++;
            }
        }
    }
//    Movido para NavalBattle
//    public boolean verifyWinner() {
//        return false;
//    }
public void setEmptyPositions(){
    for (String[] position : POSITIONS) {
        Arrays.fill(position, " ");
    }
}

    private String getLetter(int index){
        return Math.abs(index) < 10 ? this.LETTERS.substring(index, index + 1) : " ";
    }

    private int getNumber(String index){
        return index.contains(index.toUpperCase()) ? this.LETTERS.indexOf(index.toUpperCase()) : 99;
    }

}

