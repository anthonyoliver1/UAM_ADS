import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Random;
import java.util.Scanner;

public class Server {

    static DatagramSocket serverSocket;
    static DatagramPacket PlayerPacket;

    public Server() {
        try {
            serverSocket = new DatagramSocket(9876);
            System.out.println("Bem-vindo ao Jokenpô");

        } catch (Exception e) {
            System.out.println("Xi hoje não tem jogo :( ......" + e);
        }
    }

    public static void main(String args[]) throws Exception {

        byte[] messageReceived = new byte[1024];
        byte[] messageSend = new byte[1024];

        Scanner in = new Scanner(System.in);
        String[] list = {"Pedra", "Papel", "Tesoura"};

        Random random = new Random();
        int index = random.nextInt(list.length);
        int empate = 0;
        int derrota = 0;
        int vitoria = 0;

        new Server();

        while (true) {
            PlayerPacket = new DatagramPacket(messageReceived, messageReceived.length);

            serverSocket.receive(PlayerPacket);

            String textReceived = new String(PlayerPacket.getData());
            System.out.println("Você escolheu " + textReceived.trim());

            InetAddress IPAddress = PlayerPacket.getAddress();
            int porta = PlayerPacket.getPort();

            switch (textReceived.trim().toLowerCase(Locale.ROOT)) {

                case "pedra":
                    String a = list[index];

                    if (a.equals("Pedra")) {
                        empate += 1;
                        String textSend = textReceived.trim() + " Empate " + a + " e temos " + empate + " empate";
                        messageSend = textSend.getBytes();

                    } else if (a.equals("Papel")) {
                        derrota += 1;
                        String textSend = "Eu venci com " + a + " e tem voce " + derrota + " derrota";
                        messageSend = textSend.getBytes();

                    } else {
                        vitoria += 1;
                        String textSend = "Voce venceu, eu escolhi " + a + " e tem voce " + vitoria + " VITORIA";
                        messageSend = textSend.getBytes();
                    }
                    break;
                case "papel":
                    String b = list[index];

                    if (b.equals("Papel")) {
                        empate += 1;
                        String textSend = "Empate " + b + " e temos " + empate + " empate";
                        messageSend = textSend.getBytes();

                    } else if (b.equals("Tesoura")) {
                        derrota += 1;
                        String textSend = "Eu venci com " + b + " e tem voce " + derrota + " derrota";
                        messageSend = textSend.getBytes();

                    } else {
                        vitoria += 1;
                        String textSend = "Voce venceu, eu escolhi " + b + " e tem voce " + vitoria + " VITORIA";
                        messageSend = textSend.getBytes();
                    }
                    break;
                case "tesoura":
                    String c = list[index];

                    if (c.equals("Tesoura")) {
                        empate += 1;
                        String textSend = "Empate " + c + " e temos " + empate + " empate";
                        messageSend = textSend.getBytes();

                    } else if (c.equals("Pedra")) {
                        derrota += 1;
                        String textSend = "Eu venci com " + c + " e tem voce " + derrota + " derrota";
                        messageSend = textSend.getBytes();

                    } else {
                        vitoria += 1;
                        String textSend = "Voce venceu, eu escolhi " + c + " e tem voce " + vitoria + " VITORIA";
                        messageSend = textSend.getBytes();
                    }
                    break;
                default:
                    System.out.println("Opção Inválida");
            }

            DatagramPacket send = new DatagramPacket(messageSend, messageSend.length, IPAddress, porta);
            serverSocket.send(send);
        }
    }
}