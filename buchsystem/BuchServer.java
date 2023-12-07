package buchsystem;

import source.ServerSocket;
import source.Socket;

import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;

public class BuchServer {

    private int localPort;
    private ServerSocket serverSocket;


    private LinkedList<Buch> buecher = new LinkedList<>();

    public BuchServer(int localPort) {
        this.localPort = localPort;
        try {
            serverSocket = new ServerSocket(localPort);
            this.setup();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void runServer() {
        while (true) {
            try {
                Socket clientSocket = serverSocket.accept();
                if(clientSocket != null) {
                    System.out.println("Server is running");
                    clientSocket.write("OK vom Buch - Server\n");
                }
                new BuchServerThread(clientSocket, this);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    public void beendeServer() {
        try {
            serverSocket.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    private void setup() {

        this.buecher.add(new Buch("King", "ES", 34.90, 20));
        this.buecher.add(new Buch("Einstein", "Physik III", 23.00, 30));
        this.buecher.add(new Buch("Metz", "Informatik", 20.90, 35));
        this.buecher.add(new Buch("Kiesel", "Didaktik Informatik", 25.00, 10));
        this.buecher.add(new Buch("Mayer", "Wirtschaft", 15.60, 25));
    }

    public LinkedList<Buch> getBuecher() {
        return buecher;
    }

    public void setBuecher(LinkedList<Buch> buecher) {
        this.buecher = buecher;
    }

}
