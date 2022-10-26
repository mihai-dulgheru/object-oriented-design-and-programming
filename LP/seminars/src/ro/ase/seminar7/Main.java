package ro.ase.seminar7;

public class Main {
    public static void main(String[] args) {
        Bancher bancher = Bancher.getInstance();

        ContCreditor cc = (ContCreditor) bancher.deschideCont("Credit");
        assert cc != null;
        cc.setTitular("Gigel Ionescu");

        ContDebitor cd = (ContDebitor) bancher.deschideCont("Debit");
        cc.setTitular("Dorel Popescu");

        assert cd != null;
        System.out.println("Contul debitor are id " + cd.getId() + ", titular " + cd.getTitular() + " si balanta " + cd.getBalanta());

        System.out.println("Contul creditor are id " + cc.getId() + ", titular " + cc.getTitular() + " si balanta " + cc.getBalanta());

        cd.adaugaDobanda(0.15f, 12);

        System.out.println("Contul debitor are id " + cd.getId() + ", titular " + cd.getTitular() + " si balanta " + cd.getBalanta());


        Vector vector = new Vector(5);
        vector.addHandler(new Math());
        System.out.println("Suma este: " + vector.Prelucreaza(1));
        System.out.println("Produsul este: " + vector.Prelucreaza(2));
        System.out.println("Minimul este: " + vector.Prelucreaza(3));
        System.out.println("Maximul este: " + vector.Prelucreaza(4));
    }
}
