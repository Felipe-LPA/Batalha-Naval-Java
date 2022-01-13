package com.projetoFinalModulo1.navalBattle;

import com.projetoFinalModulo1.navalBattle.auxClass.Bot;
import com.projetoFinalModulo1.navalBattle.auxClass.Human;

import java.util.Objects;
import java.util.Random;
import java.util.Scanner;

public class NavalBattle {

    public static void start(){
        boolean plGame = true;
        while (plGame) {
            playGame("");
//            playGame("test");
            plGame = !(askBinaryQuestion("Fim de Jogo!", "Jogar de Novo", "Parar") == 2);
        }
        makeSpaces();
        System.out.println("Obrigado por jogar conosco!!!");
    }

    public static void playGame(String type){
        makeSpaces();

        Bot bot = initiateBot(type);
        setBotBoard(bot, type);
        makeSpaces();
        Human human = initiateHuman(type);
        setHumanBoard(human);
        makeSpaces();
        int firstPlayer = chooseWhoStarts();
        for (int i = 0; i < 3; i++) {
            waitSec(1);
            System.out.println("*flip*");
        }
        waitSec(1);
        printWhoStarts(firstPlayer == 1 ? "Você" : bot.getName());
        waitSec(2);
        makeSpaces();
        boolean followPlaying = true;
        int iteration = 0;
        String turn;
        int i = 0;
        while(followPlaying) {
            if ((iteration % 2 != 0 && firstPlayer != 1) || (iteration % 2 == 0 && firstPlayer == 1)) {
                turnHuman(bot, human, iteration + 1, type);
                makeSpaces();
                turn = human.getName();
            } else {
                int[] positionToHit = {i, 0};
                turnBot(bot, human, type, positionToHit);
                turn = bot.getName();
                i++;
            }

            followPlaying = verifyWinner(bot.getScore(), bot.board.getHowMuchShips(), human.getScore(), human.board.getHowMuchShips());
            if(!followPlaying)ShowWinner(turn);
            iteration++;
        }

        System.out.println("Hora de ver o resultado desta partida!");
        System.out.printf("Tabuleiro de %s:%n", human.getName());
        human.board.printPosition();
        System.out.printf("Tabuleiro de %s:%n", bot.getName());
        bot.board.printPosition();
    }

    private static void turnBot(Bot bot, Human human, String type, int[] positionToHit) {
        Random random = new Random();
        int column = random.nextInt(10);
        int row = random.nextInt(10);
        boolean validShot = false;
        if(Objects.equals(type, "")){
            while(!validShot){
                column = random.nextInt(10);
                row = random.nextInt(10);
                validShot = bot.board.isShotValid(row, column);
            }
        }else{

            column = positionToHit[0];
            row = positionToHit[1];
        }
        boolean hit = human.board.checkHit(row, column);
        if(hit) bot.setScore();
        bot.board.setSelfMark(row, column, hit);
        human.board.setOpponentMark(row, column);

    }
    private static void turnHuman(Bot bot, Human human, int turn, String type) {
        Random random = new Random();
        int row = random.nextInt(10);
        int column = random.nextInt(10);
        boolean validShot = false;
        while (!validShot){
            if (Objects.equals(type, "test") ) {
                System.out.println("Bot Board");
                bot.board.printPosition();
            }
            printStringLines(multiplyString("*", 45), 1);
            System.out.printf("%sRODADA %d%s%n", multiplyString(" ", 19), turn, multiplyString(" ", 19));
            printStringLines(multiplyString("*", 45), 1);
            human.board.printPosition();
            printStringLines(multiplyString("-", 44), 1);
            System.out.printf("           Score: Você %d : %d %s           %n", human.getScore(), bot.getScore(), bot.getName());
            printStringLines(multiplyString("-", 44), 1);
            String position = getInfo("Sua vez de jogar: %nInforme uma posição para atirar em seu adversário(Ex: A2, b4, c7...)%n Digite r para random%nResposta: ");
            if(!Objects.equals(position, "r") && !Objects.equals(position, "R")){
                String[] splitedPosition = position.split("");
                if (human.board.IsValidBoardLetter(splitedPosition[0]) && human.board.IsValidBoardNumber(splitedPosition[1])) {
                    String aux = splitedPosition[0].toUpperCase();
                    row = human.checkNumber(aux);
                    try {
                        column =  Integer.parseInt(splitedPosition[1]);
                        validShot = human.board.isShotValid(row, column);
                    }catch (NumberFormatException ignored){

                    }}
            } else {
                while (!validShot){
                    row = random.nextInt(10);
                    column = random.nextInt(10);
                    validShot = human.board.isShotValid(row, column);
                }
            }
            if(!validShot){
                System.out.printf("A posição %s é inválida, jogue novamente!", position);
                waitSec(2);
                makeSpaces();
            }
        }
        boolean hit = bot.board.checkHit(row, column);
        if(hit) human.setScore();
        human.board.setSelfMark(row, column, hit);
        bot.board.setOpponentMark(row, column);
    }

    private static Bot initiateBot(String type){
        Bot bot = new Bot();
        if (Objects.equals(type, "test"))bot.board.setHowMuchShips(3);
        bot.setName();
        return bot;
    }
    private static void setBotBoard(Bot bot, String type) {
        bot.board.setEmptyPositions();
        if(Objects.equals(type, "test")){
            System.out.println("Tabuleiro do Bot");
            bot.board.setShips(false);
        }else{
            bot.board.setShips(true);
        }


    }
    private static Human initiateHuman(String type){
        Human human = new Human();
        if (Objects.equals(type, "test"))human.board.setHowMuchShips(3);
        String name = getInfo("Digite seu nome: ");
        human.setName(name);
        makeSpaces();
        return human;
    }
    private static void setHumanBoard(Human human) {
        boolean setShipsAuto = !(askBinaryQuestion("É hora de escolher a posição dos navios!", "automático", "manual") == 2);
        human.board.setEmptyPositions();
        human.board.setShips(setShipsAuto);
        human.board.printPosition();
    }

    private static int chooseWhoStarts() {
        int humanCoinSide = askBinaryQuestion("Vamos lançar uma moeda para decidir quem começa o Jogo!", "cara", "coroa");
        return whoIsTheFirstPlayer(humanCoinSide);
    }
    private static int launchCoin() {
        Random random = new Random();
        // 1 - heads, 2 - tails
        return random.nextInt(2);
    }
    private static int whoIsTheFirstPlayer(int humanCoinSide) {
        int result = launchCoin() + 1;
        return humanCoinSide == result ? 1 : 2;
    }
    private static void printWhoStarts(String winner) {
        System.out.printf("%s começa o Jogo!%n", winner);
    }

    private static void ShowWinner(String turn) {
        makeSpaces();
        int howManyChars = turn.length();
        int half = Math.floorDiv((68 - howManyChars), 2);
        printStringLines(multiplyString("#", 70), 3);
        printStringLines(multiplyString("#", 30) + " VENCEDOR " + multiplyString("#", 30), 1);
        for (int i = 0; i < half; i++) {
            System.out.print("#");
        }
        System.out.printf(" %s ", turn);
        if((30 - howManyChars) % 2 != 0){
            half++;
        }
        for (int i = 0; i < half; i++) {
            System.out.print("#");
        }
        System.out.println();
        printStringLines(multiplyString("#", 70), 3);
        waitSec(4);
        makeSpaces();
    }
    private static boolean verifyWinner(int botScore, int qtdShipsBot, int humanScore, int qtdshipsHuman) {
        return !(botScore == qtdShipsBot || humanScore == qtdshipsHuman);
    }

    private static void waitSec(int sec) {
        try {
            Thread.sleep(sec * 1000L);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public static void makeSpaces() {
        System.out.printf(multiplyString("%n", 42));
    }
    private static int askBinaryQuestion(String msg, String opt1, String opt2) {
        boolean invalidOpt = true;
        int response = 1;
        System.out.println(msg);
        while(invalidOpt) {
            System.out.printf("Você prefere %s ou %s?%n", opt1, opt2);
            response = getIntInfo("Digite 1 para " + opt1 +" ou 2 para "+ opt2 + ": ");
            if (response == 1 || response == 2)  invalidOpt = false;
            else System.out.println("Opção Invalida!");
        }
        return response;
    }
    private static String multiplyString(String string, int nTimes) {
        return String.valueOf(string).repeat(Math.max(0, nTimes));
    }
    private static void printStringLines(String word, int nLines) {
        for (int i = 0; i < nLines; i++) System.out.println(word);
    }

    private static String getInfo(String msg) {
        boolean check = true;
        String response = "";
        while (check) {
            response = getInfoFromConsole(msg);
            if (response != null) {
                check = false;
            }
        }
        return response;
    }
    private static String getInfoFromConsole(String msg) {
        Scanner sc = new Scanner(System.in);
        String info;
        try {
            System.out.printf(msg);
            info = sc.nextLine();
        } catch (Exception e) {
            info = null;
        }
        return info;
    }
    private static int  getIntInfo(String msg) {
        boolean check = true;
        int response = 9999999;
        while (check) {
            response = getIntInfoFromConsole(msg);
            if (response != 9999999) {
                check = false;
            }
        }
        return response;
    }
    private static int getIntInfoFromConsole(String msg) {
        Scanner sc = new Scanner(System.in);
        int info;
        try {
            System.out.printf(msg);
            info = sc.nextInt();
        } catch (Exception e) {
            info = 9999999;
        }
        return info;
    }

}
