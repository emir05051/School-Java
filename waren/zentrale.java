Produkt
public class Produkt {
    private byte produktnr;
    private byte aktuelleAnzahl;
    private int gesamtanzahl;

    public Produkt(byte pnr){
        produktnr = pnr;
        aktuelleAnzahl = 0;
        gesamtanzahl = 0;
    }

    public void setGesamtanzahl(int gesamtanzahl) {
        this.gesamtanzahl = gesamtanzahl;
    }

    public void setAktuelleAnzahl(byte aktuelleAnzahl) {
        this.aktuelleAnzahl = aktuelleAnzahl;
    }

    public int getGesamtanzahl() {
        return gesamtanzahl;
    }

    public byte getProduktnr() {
        return produktnr;
    }

    public byte getAktuelleAnzahl() {
        return aktuelleAnzahl;
    }

}



Zentrale
import Serial;
public class Zentrale {
    private String name;
    private int port;
    private Serial com;
    private boolean istBereit;
    private Produkt[] produkte;

    public Zentrale(String n, int port){
        this.name = n;
        this.port = port;
        this.com = null;
        this.produkte = new Produkt[5];
        oeffnePort();
        //this.com.setDTR(false);
    }

    public boolean oeffnePort(){
        this.com = new Serial("COM" + this.port, 19200,8,1,2);
        istBereit = com.open();

        return istBereit;
    }

    public String aktuallisiereAusgabe(){
        String ausgabe = "";

        for (int i = 0; i < 5; i++){
            ausgabe += "P" + produkte[i].getProduktnr() + "  ";
        }

        ausgabe += "\n";

        for (int i = 0; i < 5; i++){
            ausgabe += produkte[i].getAktuelleAnzahl() + "   ";
        }

        ausgabe += "\n";

        for (int i = 0; i < 5; i++){
            ausgabe += produkte[i].getGesamtanzahl() + "   ";
        }

        ausgabe += "\n";

        return ausgabe;
    }

    public void aktuallisiereDaten(byte produktnr, byte anzahl){

        for (int i = 0; i < 5; i++){
            if (produkte[i].getProduktnr() == produktnr){
                produkte[i].setAktuelleAnzahl(anzahl);
                produkte[i].setGesamtanzahl((byte) (produkte[i].getGesamtanzahl() + anzahl));
            }
        }

        System.out.println(aktuallisiereAusgabe());

    }

    public void setProdukte(Produkt[] produkte) {
        this.produkte = produkte;
    }

    public void setDTR(boolean x){
        this.com.setDTR(x);
    }

    public Serial getCom() {
        return com;
    }

    public void schliessePort(){
        for (int i = 0; i < produkte.length; i++){
            produkte[i].setGesamtanzahl(0);
            produkte[i].setAktuelleAnzahl((byte) 0 );
        }
        this.com.close();
    }
}



ZentraleUI

public class ZentraleUI {
    public static void main(String[] args) {

        Zentrale z = new Zentrale("Zentrale" , 8);
        Produkt p1 = new Produkt((byte) 1);
        Produkt p2 = new Produkt((byte) 2);
        Produkt p3 = new Produkt((byte) 3);
        Produkt p4 = new Produkt((byte) 4);
        Produkt p5 = new Produkt((byte) 5);
        Produkt[] p = {p1,p2,p3,p4,p5};

        z.setProdukte(p);

        while (true){
            while (z.getCom().dataAvailable() > 0) {
                //Mit DTS/DTR auf low/high setzten, funktioniert der Datenaustausch nicht immer und die Daten kommen unvollständig an
                //Dadurch kommt es im Programm zu einer INDEX OUT OF BOUND fehler Meldung
                //Ohne DTR/DTS funktioniert alles einwandfrei

                /*if (z.getCom().readBytes()[0] == 0x06) {
                    z.setDTR(true);
                    System.out.println(z.aktuallisiereAusgabe());
                }*/

                    String input = z.getCom().readLine();
                    String[] command = input.split(";");

                    String convertedInput1 = command[1];
                    String convertedInput2 = command[2];

                    z.aktuallisiereDaten(Byte.parseByte(convertedInput1), Byte.parseByte(convertedInput2));

            }
        }

    }
}



Warenausgabe
import Serial;
public class Warenausgabe {
    private int port;
    private boolean istBereit;
    private Serial com;

    public Warenausgabe(int portnr){
        this.port = portnr;
        this.com = null;
        this.istBereit = false;
        oeffnePort();
    }

    public boolean oeffnePort(){
        this.com = new Serial("COM" + this.port, 19200,8,1,2);

        return this.com.open();
    }

    public boolean getDSR(){
        return com.isDSR();
    }

    public void setDTR(boolean x){
        com.setDTR(x);
    }

    public void setIstBereit(boolean istBereit) {
        this.istBereit = istBereit;
    }

    public void sendeDaten(String daten){

        String[] data = daten.split(",");

        com.write("S;" + data[0] + ";" + data[1] + ";E" + "\n");

    }

    public void schliessePort(){
        this.com.close();
    }

    public Serial getCom() {
        return com;
    }
}



WarenausgangUI
i
import java.util.Scanner;

public class WarenausgangUI {
    public static void main(String[] args) {
        Warenausgabe w = new Warenausgabe(9);
        Scanner sc = new Scanner(System.in);
        byte[] ack = {(byte) 0x06};
        while (true){

            /*if (w.getDSR() == false){
                w.getCom().write(ack, 1);
            }*/

            System.out.println("Input your Produkt with the shipped ammount: ");
            String input = sc.nextLine();

            if (input.contains(",")){
                String[] data = input.split(",");

                if (Integer.valueOf(data[0]) > 5 || Integer.valueOf(data[0]) < 1 || Integer.valueOf(data[1]) > 100 || Integer.valueOf(data[1]) < 1){
                    System.out.println("Your input was incorrect...\nTry again");
                }else{
                    w.sendeDaten(input);
                }

            }else {
                System.out.println("Your input was incorrect...\nTry again");
            }


        }

    }
}

Direkt
