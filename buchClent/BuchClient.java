package buchsystem;

import source.Socket;

import java.io.IOException;

public class BuchClient {
    private Socket clientSocket;

    public BuchClient() {

    }

    public boolean verbinden(String host, int port) {
        try {
            clientSocket = new Socket(host, port);
            clientSocket.connect();
            return clientSocket.readLine().equals("OK vom Buch - Server");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public void senden(String text) {
        try {
            clientSocket.write(text + "\n");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public String empfangen() {
        try {
            while (clientSocket.dataAvailable() == 0) {;}

            return clientSocket.readLine();
        } catch (IOException e) {
            e.printStackTrace();
            return "";
        }
    }
    public void beenden() throws IOException {
        this.clientSocket.close();
    }
}
