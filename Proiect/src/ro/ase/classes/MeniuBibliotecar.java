package ro.ase.classes;

import ro.ase.abstractClasses.Meniu;

public class MeniuBibliotecar extends Meniu {
    @Override
    public void afiseazaMeniu() {
        System.out.println();
        System.out.println("Meniu Bibliotecar");
        System.out.println("1. Adaugă carte în colecție");
        System.out.println("2. Șterge carte din colecție");
        System.out.println("3. Generează raport privind cărțile unui anumit autor");
        System.out.println("4. Generează raport privind cărțile dintr-o anumită colecție");
        System.out.println("5. Generează raport privind cărțile împrumutate la o anumită dată");
        System.out.println("0. Ieșire");
    }
}
