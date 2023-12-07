package buchsystem;

import java.util.Scanner;

public class BuchClientUI {
    private static Scanner sc = new Scanner(System.in);
    public static void main(String[] args) {
        BuchClient client = new BuchClient();
        boolean isConnected = false;
        if(client.verbinden("localhost", 2000)) {
            System.out.println("Connected");
            isConnected = true;
        }
        try{
            while (isConnected) {
//                showAll(client);
//                System.out.println(client.empfangen());
               deliver(client);
               System.out.println(client.empfangen());

               Thread.sleep(5000);
               showAll(client);
               System.out.println();
//                quit(client);
//                client.empfangen();
//                client.beenden();
//                isConnected = false;
//                showAll(client);
//                System.out.println(client.empfangen());
//                show(client);
//                System.out.println(client.empfangen());
                isConnected = false;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    private static void deliver(BuchClient client) {
        String command = sc.nextLine();
        client.senden(command + "\n");
    }
    private static void count(BuchClient client) {
        client.senden("c");
    }
    private static void show(BuchClient client) {
        String command = sc.nextLine();
        client.senden(command + '\n');
    }
    private static void showAll(BuchClient client) {
        client.senden("a\n");
    }
    private static void quit(BuchClient client) {
        client.senden("q\n");
    }
}
