package buchsystem;

public class Buch {
    private String autor;
    private String titel;
    private double preis;
    private int anzahl;

    public Buch(String autor, String titel, double preis, int anzahl)
    {
        this.autor = autor;
        this.titel = titel;
        this.preis = preis;
        this.anzahl = anzahl;
    }

    public Buch() {

    }

    public Buch(String sample) {
        String[] parameters = sample.split("\\^");
        this.autor  = parameters[0];
        this.titel  = parameters[1];
        this.preis  = Double.parseDouble(parameters[2]);
        this.anzahl = Integer.parseInt(parameters[3]);
    }

    @Override
    public String toString() {
        return "Buch{" +
                "autor='" + autor + '\'' +
                ", titel='" + titel + '\'' +
                ", preis=" + preis +
                ", anzahl=" + anzahl +
                '}';
    }
}
