package OneServerOneClient;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client {

    public static void main(String[] args) throws IOException {
        System.out.println("Client started...");
        Socket socket = new Socket("127.0.0.1", 6000);
        System.out.println("Client connected...");

        while (true) {
            ObjectOutputStream oos = new ObjectOutputStream(socket.getOutputStream());
            ObjectInputStream ois = new ObjectInputStream(socket.getInputStream());

            Scanner sc = new Scanner(System.in);

            String message = sc.nextLine();

            // sending to server
            oos.writeObject(message);

            try {
                // getting message from server
                Object fromServer = ois.readObject();
                System.out.println("From server: " + (String) fromServer);
            } catch (ClassNotFoundException e) {
                throw new RuntimeException(e);
            }
        }


    }
}
