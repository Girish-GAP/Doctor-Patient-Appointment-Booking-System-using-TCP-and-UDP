import java.net.*;

public class PatientUDPServer {

    public static void main(String[] args) {
        try {
            DatagramSocket udpSocket = new DatagramSocket(5002);
            byte[] buffer = new byte[256];

            System.out.println("Patient UDP Server started on port 5002, waiting for doctor's decision...");

            while (true) {
                DatagramPacket packet = new DatagramPacket(buffer, buffer.length);
                udpSocket.receive(packet);

                String receivedMessage = new String(packet.getData(), 0, packet.getLength());
                System.out.println("Received decision from Doctor: " + receivedMessage);

                if (receivedMessage.equalsIgnoreCase("accept") || receivedMessage.equalsIgnoreCase("deny")) {
                    break;
                }
            }

            udpSocket.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
