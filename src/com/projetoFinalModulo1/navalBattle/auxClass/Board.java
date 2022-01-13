package com.projetoFinalModulo1.navalBattle.auxClass;

import com.projetoFinalModulo1.navalBattle.NavalBattle;

import java.util.*;

public class Board {
    private final String[][] POSITIONS = new String[10][10];
    private final String LETTERS = "ABCDEFGHIJ";
    private int ships = 10;

    public int getHowMuchShips() {
        return ships;
    }
    public void setHowMuchShips(int howMuch) {
        this.ships = howMuch;
    }

    public void updatePositions(int row, int column, String mark) {
        this.POSITIONS[row][column] = mark;
    }
    public void printPosition() {
        System.out.printf("|   | 0 | 1 | 2 | 3 | 4 | 5 | 6 | 7 | 8 | 9 |%n");
        for (int i = 0; i < this.POSITIONS.length; i++) {
            System.out.printf("| %s | %s |%n" ,this.getLetter(i), String.join(" | ", this.POSITIONS[i]));
        }
    }

    public boolean isValidPosition(int row, int column) {
        return Objects.equals(this.POSITIONS[row][column], " ");
    }


    public void setShips(boolean isAuto) {
        int i = 0;

        while (i < this.ships) {
                Random random = new Random();
                int column = random.nextInt(10);
                int row = random.nextInt(10);
            if(!isAuto){
                Scanner sc = new Scanner(System.in);
                NavalBattle.makeSpaces();
                this.printPosition();
                System.out.printf("Para posicionar o navio %d escolha uma das opções: %nInforme uma posição ainda não preenchida em formato válido(Ex: A2, b4, c7...) %nDigite r para random %nopç: ", i+1);
                String position = sc.nextLine();
                if(!Objects.equals(position, "r") && !Objects.equals(position, "R")){
                    String[] splitedPosition = position.split("");
                    row = this.getNumber(splitedPosition[0].toUpperCase());
                    column =  Integer.parseInt(splitedPosition[1]);
                }
            }
            if (row != 99 && this.isValidPosition(row, column)) {
                this.updatePositions(row, column, "N");
                i++;
            }
        }
    }

    public void setEmptyPositions(){
        for (String[] position : POSITIONS) {
            Arrays.fill(position, " ");
        }
    }

    public String getLetter(int index){
        return Math.abs(index) < 10 ? this.LETTERS.substring(index, index + 1) : " ";
    }
    public int getNumber(String index){
        return this.LETTERS.contains(index.toUpperCase())? this.LETTERS.indexOf(index.toUpperCase()) : 99;
    }
    public boolean IsValidBoardLetter(String letter) {
        if (letter.length() != 1 ) return false;
        return this.LETTERS.contains(letter.toUpperCase());
    }
    public boolean IsValidBoardNumber(String number) {
        if (number.length() != 1 ) return false;
        String  numbers = "0123456789";
        return numbers.contains(number);
    }
    public boolean isShotValid(int row, int column) {
        return Objects.equals(this.POSITIONS[row][column], " ") ||
                Objects.equals(this.POSITIONS[row][column], "N");
    }
    public boolean checkHit(int row, int column){
        String mark = this.POSITIONS[row][column];
        return Objects.equals(mark, "N") || Objects.equals(mark, "X") || Objects.equals(mark, "n");
    }

    public void setSelfMark(int row, int column, boolean hit) {
        String previousMark = this.POSITIONS[row][column];
        if(hit) {
            if (Objects.equals(previousMark, " ")) {
                this.updatePositions(row, column, "*");
            } else if (Objects.equals(previousMark, "N")) {
                this.updatePositions(row, column, "X");
            }
        }
        else{
            if (Objects.equals(previousMark, " ")){
                this.updatePositions(row, column, "-");
            }
            else if (Objects.equals(previousMark, "N")){
                this.updatePositions(row, column, "n");
            }
        }
    }


    public void setOpponentMark(int row, int column) {
        String previousMark = this.POSITIONS[row][column];
        if (Objects.equals(previousMark, "N")){
            this.updatePositions(row, column, " ");
        }
        else if (Objects.equals(previousMark, "X")){
            this.updatePositions(row, column, "*");
        }
        else if (Objects.equals(previousMark, "n")){
            this.updatePositions(row, column, "-");
        }
    }
}

