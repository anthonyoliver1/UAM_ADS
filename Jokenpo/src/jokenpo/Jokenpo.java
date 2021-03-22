package jokenpo;

import java.util.Locale;
import java.util.Random;
import java.util.Scanner;


public class Jokenpo {

    public static void main(String[] args) {
        Scanner in = new Scanner(System.in); // Recebendo valores inteiros
        Scanner s = new Scanner(System.in); // Recebendo Strings
        Random random = new Random(); // Gerando valores aleatórios
        int typeGame;

        System.out.println("Selecione o tipo de jogo");
        System.out.println("1 - Jogador VS Jogador");
        System.out.println("2 - Jogador VS Máquina");
        typeGame = in.nextInt();

        String playerOne;
        String playerTwo;

        String[] list = {"Pedra", "Papel", "Tesoura"};

        switch (typeGame) {
//            Jogador VS Jogador
            case 1:
                System.out.println("Jogador VS Jogador");
                System.out.print("Esolha: Pedra, Papel ou Tesoura");
                System.out.println(" ");

                System.out.println("Jogado 1");
                playerOne = s.nextLine().toLowerCase(Locale.ROOT);
                System.out.println(playerOne);

                System.out.println("Jogador 2");
                playerTwo = s.nextLine().toLowerCase(Locale.ROOT);

                switch (playerOne) {
                    case "pedra":
                        switch (playerTwo) {
                            case "pedra":
                                System.out.println("Empatou");
                                break;
                            case "papel":
                                System.out.println("Jogadr 2 venceu!!");
                                break;
                            case "tesoura":
                                System.out.println("jogador 1 venceu!!");
                                break;
                            default:
                                System.out.println("De novo");
                        }
                        break;
                    case "papel":
                        switch (playerTwo) {
                            case "pedra":
                                System.out.println("Jogadr 2 venceu!!");
                                break;
                            case "papel":
                                System.out.println("Empatou");
                                break;
                            case "tesoura":
                                System.out.println("jogador 1 venceu!!");
                                break;
                            default:
                                System.out.println("De novo");
                        }
                        break;
                    case "tesoura":
                        switch (playerTwo) {
                            case "pedra":
                                System.out.println("Jogadr 2 venceu!!");
                                break;
                            case "papel":
                                System.out.println("jogador 1 venceu!!");
                                break;
                            case "tesoura":
                                System.out.println("Empatou");
                                break;
                            default:
                                System.out.println("De novo");
                        }
                        break;
                    default:
                        System.out.println("Digite com todas as letras minusculas e sem número");
                }
                break;
            case 2:
//                Jogador VS Máquina
                System.out.println("Jogador VS Máquina");
                System.out.println("Esolha: Pedra, Papel ou Tesoura");
                System.out.println("Jogador 1");
                playerOne = s.nextLine().toLowerCase(Locale.ROOT);

                int index = random.nextInt(list.length);

                switch (playerOne) {
                    case "pedra":
                        String a = list[index];
                        System.out.println(a);

                        if (a.equals("Pedra")) {
                            System.out.println("Empatou");
                        } else if (a.equals("Papel")) {
                            System.out.println("Máquina Venceu");
                        } else {
                            System.out.println("jogado 1 Venceu");
                        }
                        break;
                    case "papel":
                        String b = list[index];
                        System.out.println(b);

                        if (b.equals("Papel")) {
                            System.out.println("Empatou");
                        } else if (b.equals("Tesoura")) {
                            System.out.println("Máquina Venceu");
                        } else {
                            System.out.println("Jogador 1 Venceu");
                        }
                        break;
                    case "tesoura":
                        String c = list[index];
                        System.out.println(c);

                        if (c.equals("Tesoura")) {
                            System.out.println("Empatou");
                        } else if (c.equals("Pedra")) {
                            System.out.println("Máquina Venceu");
                        } else {
                            System.out.println("Jogador 1 Venceu");
                        }
                        break;
                    default:
                        System.out.println("Opção Inválida");
                }
                break;
            default:
                System.out.println("Opção Inválida");
        }
    }
}