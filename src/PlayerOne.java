import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

public class PlayerOne {

    static DatagramSocket PlayerOnePacket;
    static InetAddress enderecoIP;

    public PlayerOne() throws Exception {
        try {
            PlayerOnePacket = new DatagramSocket();
            enderecoIP = InetAddress.getByName("localhost");
        } catch (Exception e) {
            System.out.println("Nao consegui resolver o host...");
        }
    }

    public static void main(String args[]) throws Exception {
        BufferedReader entradaUsuario
                = new BufferedReader(new InputStreamReader(System.in));

        byte[] sendData = new byte[1024];
        byte[] receiveData = new byte[1024];
        System.out.println("Vamos jogar ");
        System.out.println("Pedra - Papel - Tesoura");
        System.out.println("Escolha: ");
        String textoAEnviar = entradaUsuario.readLine();
        System.out.println("");

        sendData = textoAEnviar.getBytes();

        new PlayerOne();
        DatagramPacket pacoteEnvio = new DatagramPacket(sendData, sendData.length, enderecoIP, 9876);
        PlayerOnePacket.send(pacoteEnvio);

        DatagramPacket pacoteRecebido = new DatagramPacket(receiveData, receiveData.length);
        PlayerOnePacket.receive(pacoteRecebido);

        String textReceived = new String(pacoteRecebido.getData());
        System.out.println("jokenpô: " + textReceived.trim());
        PlayerOnePacket.close();
    }
}