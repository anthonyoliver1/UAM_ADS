import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;
import java.nio.charset.StandardCharsets;

public class PlayerTwo {

    static DatagramSocket PlayerTwoPacket;
    static InetAddress enderecoIP;

    public PlayerTwo() throws Exception {
        try {
            PlayerTwoPacket = new DatagramSocket();
            enderecoIP = InetAddress.getByName("localhost");
        } catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader entradaUsuario = new BufferedReader(new InputStreamReader(System.in));
        new PlayerTwo();
        byte[] sendData = new byte[1024];
        byte[] sendDataP2 = new byte[1024];
        byte[] receiveData = new byte[1024];


        sendDataP2 = "true".getBytes();

        DatagramPacket pacoteEnvio = new DatagramPacket(sendDataP2, sendDataP2.length, enderecoIP, 9876);
        PlayerTwoPacket.send(pacoteEnvio);

        System.out.println("Vamos jogar jogador 2 ");
        System.out.println("Pedra - Papel - Tesoura");
        System.out.println("Escolha: ");
        String textoAEnviar = entradaUsuario.readLine();
        System.out.println("");

        sendDataP2 = textoAEnviar.getBytes();


//        DatagramPacket pacoteEnvio = new DatagramPacket pacoteEnvio = new DatagramPacket(sendDataP2, sendDataP2.length, enderecoIP, 9876);
//        PlayerTwoPacket.send(pacoteEnvio);

        DatagramPacket pacoteRecebido = new DatagramPacket(receiveData, receiveData.length);
        PlayerTwoPacket.receive(pacoteRecebido);

        String textReceived = new String(pacoteRecebido.getData());
        System.out.println("jokenpô p2: " + textReceived.trim());
        PlayerTwoPacket.close();
    }
}