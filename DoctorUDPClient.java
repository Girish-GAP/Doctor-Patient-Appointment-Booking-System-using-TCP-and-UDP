import java.net.*;

public class DoctorUDPClient {

    public static void sendUDPMessage(String message, String ipAddress, int port) {
        try {
            DatagramSocket udpSocket = new DatagramSocket();
            InetAddress inetAddress = InetAddress.getByName(ipAddress);
            byte[] buffer = message.getBytes();

            DatagramPacket packet = new DatagramPacket(buffer, buffer.length, inetAddress, port);
            udpSocket.send(packet);
            System.out.println("Sent decision to Patient via UDP: " + message);
            udpSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
