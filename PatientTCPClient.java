import java.io.*;
import java.net.*;
import java.util.Scanner;

public class PatientTCPClient {

    private Socket socket;
    private DataOutputStream output;

    public PatientTCPClient(String address, int port) {
        try {
            // Connect to the Doctor TCP Server
            socket = new Socket(address, port);
            System.out.println("Connected to Doctor TCP Server");

            // Get input from user
            Scanner scanner = new Scanner(System.in);
            System.out.print("Enter your name: ");
            String name = scanner.nextLine();
            System.out.print("Enter the appointment date (e.g., tomorrow): ");
            String date = scanner.nextLine();
            System.out.print("Enter the appointment time (e.g., 10:00 AM): ");
            String time = scanner.nextLine();

            // Prepare the appointment details
            String appointmentDetails = "Patient " + name + " wants an appointment on " + date + " at " + time;

            // Send appointment details to doctor
            output = new DataOutputStream(socket.getOutputStream());
            output.writeUTF(appointmentDetails);
            System.out.println("Sent appointment details to Doctor: " + appointmentDetails);

            // Close connection
            output.close();
            socket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        PatientTCPClient client = new PatientTCPClient("127.0.0.1", 5001);
    }
}
