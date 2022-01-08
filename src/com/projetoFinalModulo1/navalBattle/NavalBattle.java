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
            playGame();
            plGame = !(askBinaryQuestion("Fim de Jogo!", "Jogar de Novo", "Parar") == 2);
        }
        System.out.println("Obrigado por jogar conosco!!!");
    }

    public static void playGame(){

        Bot bot = initiateBot();
        setBotBoard(bot);

        Human human = initiateHuman();
        setHumanBoard(human);

        int firstPlayer = chooseWhoStarts();
        printWhoStarts(firstPlayer == 1 ? "Você" : bot.getName());

        boolean followPlaying = true;
        int iteration = 0;
        String turn;
        while(followPlaying) {
            if ((iteration % 2 == 0 && firstPlayer != 1) || (iteration % 2 != 0 && firstPlayer == 1)) {
                turnHuman(bot, human);
//                turn = human.getName();
            } else {
                turnBot(bot, human);
//                turn = bot.getName();
            }
            iteration++;
            followPlaying = verifyWinner(bot.getScore(), human.getScore());
        }
        System.out.println("Hora de ver o resultado desta partida!");
        System.out.printf("Tabuleiro de %s:%n", human.getName());
        human.board.printPosition();
        System.out.printf("Tabuleiro de %s:%n", bot.getName());
        bot.board.printPosition();
    }

    private static boolean verifyWinner(int botScore, int humanScore) {
        // This method needs to be developed
        return !(botScore == 10 || humanScore == 10);
    }
    private static void turnBot(Bot bot, Human human) {
//        bot.board.printPosition();
        Random random = new Random();
        int column = random.nextInt(10);
        int row = random.nextInt(10);
        boolean validShot = false;
        while(!validShot){
            column = random.nextInt(10);
            row = random.nextInt(10);
            validShot = bot.board.isShotValid(row, column);
        }
        boolean hit = human.board.checkHit(row, column);
        if(hit) bot.setScore();
        bot.board.setSelfMark(row, column, hit);
        human.board.setOpponentMark(row, column);
    }
    private static void turnHuman(Bot bot, Human human) {
        Scanner sc = new Scanner(System.in);
        Random random = new Random();
        int row = random.nextInt(10);
        int column = random.nextInt(10);
        boolean validShot = false;
        while (!validShot){
            human.board.printPosition();
            System.out.printf("Score: Você %d : %d %s%n", human.getScore(), bot.getScore(), bot.getName());
            System.out.printf("Sua vez de jogar: %nInforme uma posição para atirar em seu adversário(Ex: A2, b4, c7...)%n Digite r para random%nopç: \"");
            String position = sc.nextLine();
            if(!Objects.equals(position, "r") && !Objects.equals(position, "R")){
                String[] splitedPosition = position.split("");
                String aux = splitedPosition[0].toUpperCase();
                row = human.checkNumber(aux);
                try {
                    column =  Integer.parseInt(splitedPosition[1]);
                    validShot = human.board.isShotValid(row, column);
                }catch (NumberFormatException exception){
                    
                }
            }else{
                row = random.nextInt(10);
                column = random.nextInt(10);
                validShot = human.board.isShotValid(row, column);
            }
            if(!validShot){
                System.out.printf("A posição %s é inválida, jogue novamente!", position);
            }

        }
        boolean hit = bot.board.checkHit(row, column);
        if(hit) human.setScore();
        human.board.setSelfMark(row, column, hit);
        bot.board.setOpponentMark(row, column);
    }

    private static Bot initiateBot(){
        Bot bot = new Bot();
        bot.setName();
        return bot;
    }
    private static void setBotBoard(Bot bot) {
        bot.board.setEmptyPositions();
        bot.board.setShips(true);

    }
    private static Human initiateHuman(){
        Scanner sc = new Scanner(System.in);
        Human human = new Human();
        System.out.print("Digite seu nome: ");
        String name = sc.nextLine();
        human.setName(name);
        return human;
    }
    private static void setHumanBoard(Human human) {
        boolean setShipsAuto = !(askBinaryQuestion("É hora de escolher a posição dos navios!", "automático", "manual") == 2);
        human.board.setEmptyPositions();
        human.board.setShips(setShipsAuto);
        human.board.printPosition();
    }
    private static int askBinaryQuestion(String msg, String opt1, String opt2) {
        Scanner sc = new Scanner(System.in);
        boolean invalidOpt = true;
        int response = 1;
        System.out.println(msg);
        while(invalidOpt) {
            System.out.printf("Você prefere %s ou %s?%n", opt1, opt2);
            System.out.printf("Digite 1 para %s ou 2 para %s: ", opt1, opt2);
            response = Integer.parseInt(sc.nextLine());
            if (response == 1 || response == 2)  invalidOpt = false;
            else System.out.println("Opção Invalida!");
        }
        return response;
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


}
