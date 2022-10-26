package ro.ase.seminar7;

public final class Bancher {
    private static Bancher instance;
    private int nextId;

    private Bancher() {
        nextId = 1001;
    }

    public static Bancher getInstance() {
        if (instance == null) {
            instance = new Bancher();
        }
        return instance;
    }

    public ContBancar deschideCont(String tip) {
        ContBancar cont;
        if (tip.equals("Debit")) {
            cont = new ContDebitor();
        } else if (tip.equals("Credit")) {
            cont = new ContCreditor();
        } else {
            return null;
        }
        cont.id += nextId++;
        return cont;
    }

}
