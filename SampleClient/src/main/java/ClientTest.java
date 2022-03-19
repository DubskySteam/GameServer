import java.io.*;
import java.net.*;
import java.util.Scanner;

/**
 * @author Clemens
 */
public class ClientTest {

    static Socket socket;

    static class Client extends Thread {

        PrintWriter writer;
        public Client(Socket socket) {
            try {
                writer = new PrintWriter(socket.getOutputStream(), true);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        public void run() {
            try {
                while (true) {
                    Scanner io = new Scanner(System.in);
                    String tst = io.nextLine();
                    writer.println(tst);
                    writer.flush();
                }
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    static class Listener extends Thread {
        BufferedReader reader;

        public Listener(Socket socket) {
            try {
                reader = new BufferedReader(new InputStreamReader(socket.getInputStream()));
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        public void run() {
            String line;
            while (true) {
                try {
                    line = reader.readLine();
                    if ((line == null) || line.equalsIgnoreCase("QUIT")) {
                        System.out.println("[SOCKET] Socket disconnected");
                        System.exit(0);
                    } else {
                        System.out.println(line + "\n\r");
                    }
                } catch (IOException e) {
                    System.out.println("[SOCKET] Socket disconnected");
                    System.exit(0);
                }
            }
        }

    }

    public static void main(String[] args) {

        try {
            socket = new Socket("185.194.217.213", 25565);
            Thread t1 = new Thread(new Client(socket));
            Thread t2 = new Thread(new Listener(socket));
            t1.start();
            t2.start();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }

}
