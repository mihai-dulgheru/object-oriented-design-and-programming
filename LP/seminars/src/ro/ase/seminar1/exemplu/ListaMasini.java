package ro.ase.seminar1.exemplu;

import java.util.ArrayList;
import java.util.List;

/**
 * Clasa pentru a stoca o colecție de obiecte {@link Masina}
 *
 * @author Cristian
 * @version 1.0
 */

public class ListaMasini {

    List<Masina> listaMasini = new ArrayList<>();

    /**
     * metoda pentru adăugare element nou în colecție
     *
     * @param m - elementul nou de adăugat
     */
    private void adaugaMasina(Masina m) {
        listaMasini.add(m);
    }

    /**
     * metoda pentru ștergere element din colecție
     *
     * @param m - elementul de șters
     */
    private void stergeMasina(Masina m) {
        listaMasini.remove(m);
    }

    /**
     * metoda pentru afișare colecție de obiecte
     */
    private void afisareListaMasini() {
        for (Masina m : listaMasini)
            System.out.println(m);
    }
}
