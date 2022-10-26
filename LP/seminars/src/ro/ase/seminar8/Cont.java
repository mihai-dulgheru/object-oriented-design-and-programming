package ro.ase.seminar8;

public class Cont {
    public static int balanta = 1000;
    public static int cheltuieli = 0;
    private String titular;
    private String IBAN;

    public Cont(String titular, String IBAN) {
        this.titular = titular;
        this.IBAN = IBAN;
    }

    public static synchronized void retragere(int valoare) {
        if (balanta >= valoare) {
            System.out.println("Valoare de retras: " + valoare);
            balanta -= valoare;
            cheltuieli += valoare;
            System.out.println("Balantă: " + balanta);
            System.out.println("Cheltuieli: " + cheltuieli);
        } else {
            System.out.println("Respins: " + valoare);
        }
    }

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getIBAN() {
        return IBAN;
    }

    public void setIBAN(String IBAN) {
        this.IBAN = IBAN;
    }
}
