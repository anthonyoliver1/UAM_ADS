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

        System.out.println("Como que jogar?");
        System.out.println("1 - Jogador vs Jogador");
        System.out.println("2 - Máquina vs Jogador");
        System.out.print("Escolha: ");
        int value = in.nextInt();

        if(value == 1){

        }
        else if (value == 2) {
            System.out.println("Máquina vs Jogador Selecionado");
            System.out.println();
            System.out.println("Vamos jogar ");
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
            PlayerPacket.close();
        }
    }
}