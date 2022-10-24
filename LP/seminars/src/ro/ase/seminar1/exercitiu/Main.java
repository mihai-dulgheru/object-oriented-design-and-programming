package ro.ase.seminar1.exercitiu;

import java.util.Date;

public class Main {

    public static void main(String[] args) {
        Curs curs1 = null;
        Curs curs2 = null;
        Curs curs3 = null;
        try {
            curs1 = new Curs("Profesor1", "PPOO", new Date(), 1, Facultate.CSIE, new Integer[]{10, 9, 8, 7, 6, 5});
            curs2 = new Curs("Profesor2", "TIC", new Date(), 2, Facultate.CIG, new Integer[]{10, 9, 8, 7, 6, 5});
            curs3 = new Curs("Profesor3", "PPOO", new Date(), 1, Facultate.REI, new Integer[]{10, 9, 8, 7, 6, 5});
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

        ListaCursuri listaCursuri = new ListaCursuri();
        listaCursuri.adaugaCurs(curs1);
        listaCursuri.adaugaCurs(curs2);
        listaCursuri.adaugaCurs(curs3);


        System.out.println(listaCursuri);
    }
}
