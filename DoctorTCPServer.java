import java.io.*;
import java.net.*;

public class DoctorTCPServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream input;
    private String appointmentDetails;

    public DoctorTCPServer(int port) {
        try {
            // Start the TCP Server
            serverSocket = new ServerSocket(port);
            System.out.println("Doctor TCP Server started on port " + port);

            // Wait for client connection
            socket = serverSocket.accept();
            System.out.println("Patient TCP connection accepted");

            // Read appointment details from client
            input = new DataInputStream(socket.getInputStream());
            appointmentDetails = input.readUTF();
            System.out.println("Received appointment details: " + appointmentDetails);

            // Doctor reviews appointment and makes a decision (accept or deny)
            BufferedReader reader = new BufferedReader(new InputStreamReader(System.in));
            System.out.print("Enter 'accept' or 'deny' for the appointment: ");
            String decision = reader.readLine();

            // Send decision to the patient via UDP
            DoctorUDPClient.sendUDPMessage(decision, "127.0.0.1", 5002);

            // Close connections
            input.close();
            socket.close();
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        DoctorTCPServer server = new DoctorTCPServer(5001);
    }
}
