import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Random;

public class Server {

    static DatagramSocket serverSocket;
    static DatagramPacket PlayerOnePacket;

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

        String[] list = {"Pedra", "Papel", "Tesoura"};

        Random random = new Random();
        int index = random.nextInt(list.length);


        new Server();

        while (true) {
            PlayerOnePacket = new DatagramPacket(messageReceived, messageReceived.length);

            serverSocket.receive(PlayerOnePacket);

            String textReceived = new String(PlayerOnePacket.getData());
            System.out.println("Você escolheu " + textReceived.trim());

            InetAddress IPAddress = PlayerOnePacket.getAddress();
            int porta = PlayerOnePacket.getPort();

            String textSend = "Escolhido " + textReceived.trim();
            messageSend = textSend.getBytes();

            System.out.println(textReceived);
            switch (textReceived.trim().toLowerCase(Locale.ROOT)) {
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

            DatagramPacket send = new DatagramPacket(messageSend, messageSend.length, IPAddress, porta);
            serverSocket.send(send);
        }
    }
}
