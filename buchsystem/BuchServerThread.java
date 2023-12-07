package buchsystem;

import source.Socket;

import java.io.IOException;

public class BuchServerThread extends Thread{

    private Socket clientSocket;
    private BuchServer bs;

    public BuchServerThread(Socket clientSocket, BuchServer bs){
        this.clientSocket = clientSocket;
        this.bs = bs;

        this.start();
    }

    @Override
    public void run() {
        while(true){
            try {
                while (clientSocket.dataAvailable() > 0) { // Важно!!!
                    String command = this.clientSocket.readLine();
                    execute(command);
                }
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void execute(String command) {
        String[] commands = command.split(" ");

        try {

            switch (commands[0]) {
                case "d":
                    for(Buch buch : this.bs.getBuecher()) {
                        System.out.println(buch.toString());
                    }
                    synchronized (this.bs.getBuecher()) {
                        this.bs.getBuecher().add(new Buch(commands[1]));
                    }
                    for(Buch buch : this.bs.getBuecher()) {
                        System.out.println(buch.toString());
                    }
                    break;
                case "c":
                    clientSocket.write(this.bs.getBuecher().size());
                    break;
                case "s":
                    Buch search = this.bs.getBuecher().get(Integer.parseInt(commands[1]));
                    clientSocket.write(search == null ? "No book\n" : (search.toString() + "\n"));
                    break;
                case "a":
                    if(this.bs.getBuecher().isEmpty()) {
                        clientSocket.write("No books\n");
                        break;
                    }
                    String tmp = "";
                    for(Buch buch : this.bs.getBuecher()) {
                        tmp += buch.toString();
                    }
                    clientSocket.write(tmp + "\n");
                    System.out.println(tmp);
                    break;
                case "q":
                    this.clientSocket.write("OK vom Buch-Server und tschuess\n");
                    this.bs.beendeServer();
                    break;
                default:
                    clientSocket.write("Kein passendes Kommando erhalten!\n");
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}
