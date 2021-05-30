import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Scanner;

public class Player {

    static DatagramSocket PlayerPacket;
    static InetAddress enderecoIP;

    public Player() throws Exception {
        try {
            PlayerPacket = new DatagramSocket();
            enderecoIP = InetAddress.getByName("localhost");
        } catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));
        Scanner in = new Scanner(System.in);

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];

        System.out.print("Digite o seu nome: ");
        String player = in.nextLine();
        boolean again = true;
        int empate = 0;
        int derrota = 0;
        int vitoria = 0;

        System.out.println();
        System.out.println("Vamos jogar " + player);
        while (again) {
            System.out.println("Escolha :");
            System.out.println("Pedra - Papel - Tesoura");
            System.out.print("Escolha: ");
            String textoAEnviar = entradaUsuario.readLine();
            System.out.println("");

            sendData = textoAEnviar.getBytes();

            new Player();

            DatagramPacket pacoteEnvio = new DatagramPacket(sendData, sendData.length, enderecoIP, 9876);
            PlayerPacket.send(pacoteEnvio);

            DatagramPacket pacoteRecebido = new DatagramPacket(receiveData, receiveData.length);
            PlayerPacket.receive(pacoteRecebido);

            String textReceived = new String(pacoteRecebido.getData());
            System.out.println("jokenpô: " + textReceived.trim());
            if(textReceived.trim().contains("empate")){
                empate += 1;
            }else if(textReceived.trim().contains("derrota")){
                derrota += 1;
            }else if(textReceived.trim().contains("vitoria")){
                vitoria += 1;
            }

            System.out.println("Jogar novamente?");
            String newGame = in.nextLine();
            if (newGame.equals("sim")) {
                again = true;
            } else {
                System.out.println("Quer ver o placar?");
                String placar = in.nextLine();
                if(placar.equals("sim")){
                    System.out.println("teve "+ empate + " empate(s)");
                    System.out.println("teve "+ derrota + " derrota(s)");
                    System.out.println("teve "+ vitoria + " vitoria(s)");
                    System.out.println();
                    System.out.println("Até a próxima :) " + player);
                    again = false;

                }else {
                    System.out.println("Até a próxima :) " + player);
                    again = false;
                }
            }
        }
        PlayerPacket.close();
    }
}