package buchsystem;

public class BuchServerUI {

    public static void main(String[] args) {
        BuchServer bs = new BuchServer(2000);

        bs.runServer();
    }
}
