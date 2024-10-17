import java.io.*;
import java.net.*;
import java.nio.file.*;
import java.util.*;

public class DoctorTCPServer {

    private ServerSocket serverSocket;
    private Socket socket;
    private DataInputStream input;
    private String appointmentDetails;
    private final String filePath = "confirmed_appointments.txt"; // File to store confirmed appointments

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

            // If the doctor accepts, save the appointment details to a file
            if (decision.equalsIgnoreCase("accept")) {
                saveAppointment(appointmentDetails);
            }

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

    // Method to save confirmed appointments to a file
    private void saveAppointment(String details) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            writer.write(details + "\n");
            System.out.println("Appointment confirmed and saved to file.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    // Method to display all confirmed appointments from the file
    public void displayConfirmedAppointments() {
        try {
            int cnt = 0;
            List<String> appointments = Files.readAllLines(Paths.get(filePath));
            System.out.println("\n---------------------------");
            System.out.println("| Confirmed Appointments: |");
            System.out.println("---------------------------\n");
            for (String appointment : appointments) {
                System.out.println(cnt + " " + appointment);
                cnt++;
            }
            System.out.println("\n---------------------------\n");
        } catch (IOException e) {
            System.out.println("No confirmed appointments yet.");
        }
    }

    public static void main(String[] args) {
        DoctorTCPServer server = new DoctorTCPServer(5001);
        // To display confirmed appointments after handling current appointment
        server.displayConfirmedAppointments();
    }
}
