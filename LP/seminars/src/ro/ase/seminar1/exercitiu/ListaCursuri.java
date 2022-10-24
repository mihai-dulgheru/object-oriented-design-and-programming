package ro.ase.seminar1.exercitiu;

import java.util.ArrayList;
import java.util.List;

public class ListaCursuri {
    List<Curs> listaCursuri = new ArrayList<>();

    /**
     * metoda pentru adaugare element nou in colectie
     *
     * @param c elementul nou de adaugat
     */
    public void adaugaCurs(Curs c) {
        listaCursuri.add(c);
    }

    public void stergeCurs(Curs c) {
        listaCursuri.remove(c);
    }

    @Override
    public String toString() {
        String sb = "ListaCursuri{" + "listaCursuri=" + listaCursuri + '}';
        return sb;
    }
}
