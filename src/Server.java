import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.util.Locale;
import java.util.Random;

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
            System.out.println("P1 - Você escolheu " + textReceived.trim());

            InetAddress IPAddress = PlayerPacket.getAddress();
            int porta = PlayerPacket.getPort();


            int[] myIntArray = new int[2];
            myIntArray[1] = empate;

            System.out.println(textReceived);

            if(textReceived.trim().equals("true")){
                System.out.println("P2 Iniciado");
            } else {
                System.out.println("FOI SWITCH");

                switch (textReceived.trim().toLowerCase(Locale.ROOT)) {

                    case "pedra":
                        String a = list[index];
                        System.out.println(a + "P1");

                        if (a.equals("Pedra")) {
                            String textSend = "Empate " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            empate += 1;
                            System.out.println(empate + " EMPATOU ");

                        } else if (a.equals("Papel")) {
                            String textSend = "Eu venci " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            derrota += 1;
                            System.out.println(derrota + "DERROTA");

                        } else {
                            String textSend = "Voce venceu " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            System.out.println(vitoria);
                            vitoria += 1;
                            System.out.println(vitoria + "VICTORIA");
                        }
                        break;
                    case "papel":
                        String b = list[index];
                        System.out.println(b + "P1");

                        if (b.equals("Papel")) {
                            String textSend = "Empate " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            empate += 1;

                        } else if (b.equals("Tesoura")) {
                            String textSend = "Eu venci " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            derrota += 1;
                            System.out.println(derrota + "DERROTA");

                        } else {
                            String textSend = "Voce venceu " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            System.out.println(vitoria);
                            vitoria += 1;
                            System.out.println(vitoria + "VICTORIA");
                        }
                        break;
                    case "tesoura":
                        String c = list[index];
                        System.out.println(c + "P1");

                        if (c.equals("Tesoura")) {
                            String textSend = "Empate " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            empate += 1;

                        } else if (c.equals("Pedra")) {
                            String textSend = "Eu venci " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            derrota += 1;
                            System.out.println(derrota + "DERROTA");

                        } else {
                            String textSend = "Voce venceu " + textReceived.trim();
                            messageSend = textSend.getBytes();
                            System.out.println(vitoria);
                            vitoria += 1;
                            System.out.println(vitoria + "VICTORIA");
                        }
                        break;
                    default:
                        System.out.println("Opção Inválida");

                }
            }
            DatagramPacket send = new DatagramPacket(messageSend, messageSend.length, IPAddress, porta);
            serverSocket.send(send);
        }
    }
}
