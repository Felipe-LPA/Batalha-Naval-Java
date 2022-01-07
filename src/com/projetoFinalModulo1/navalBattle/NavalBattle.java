package com.projetoFinalModulo1.navalBattle;

import com.projetoFinalModulo1.navalBattle.auxClass.Bot;
import com.projetoFinalModulo1.navalBattle.auxClass.Human;

import java.util.Random;
import java.util.Scanner;
import java.util.function.Consumer;

public class NavalBattle {

    public static void start(){
        String name = "";
        boolean plGame = true;
        while (plGame) {
            name = playGame(name);
            plGame = !(askBinaryQuestion("Fim de Jogo!", "Jogar de Novo", "Parar") == 2);
        }
        System.out.println("Obrigado por jogar conosco!!!");
    }

    public static String playGame(String name){

        Bot bot = initiateBot();
        bot = setBotBoard(bot);

        Human human = initiateHuman(name);
        human = setHumanBoard(human);
        name = human.getName();

        int firstPlayer = chooseWhoStarts();
        printWhoStarts(firstPlayer == 1 ? "Você" : bot.getName());

        boolean followPlaying = true;
        int iteration = 0;
        while(followPlaying) {
            if (iteration % 2 == 0) {
                if (firstPlayer == 1) turnHuman();
                else turnBot();
            } else {
                if (firstPlayer == 1) turnBot();
                else turnHuman();
            }
            iteration++;
            followPlaying = verifyWinner();
        }
        System.out.println("Hora de ver o resultado desta partida!");
        System.out.printf("Tabuleiro de %s:%n", human.getName());
        human.board.printPosition();
        System.out.printf("Tabuleiro de %s:%n", bot.getName());
        bot.board.printPosition();
        return name;
    }

    private static boolean verifyWinner() {
        // This method needs to be developed
        return launchCoin() == 1;
    }
    private static int turnBot() {
        // This method needs to be developed
        return 1;
    }
    private static int turnHuman() {
        // This method needs to be developed
        return 1;
    }

    private static Bot initiateBot(){
        Bot bot = new Bot();
        bot.setName();
        return bot;
    }
    private static Bot setBotBoard(Bot bot) {
        bot.board.setEmptyPositions();
        bot.board.setShips(true);
        return bot;
    }
    private static Human initiateHuman(String name){
        Scanner sc = new Scanner(System.in);
        Human human = new Human();
        if (name == "") {
            System.out.print("Digite seu nome: ");
            name = sc.nextLine();
        }
        human.setName(name);
        return human;
    }
    private static Human setHumanBoard(Human human) {
        boolean setShipsAuto = !(askBinaryQuestion("É hora de escolher a posição dos navios!", "automático", "manual") == 2);
        human.board.setEmptyPositions();
        human.board.setShips(setShipsAuto);
        human.board.printPosition();
        return human;
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
