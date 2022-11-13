package ro.ase.classes;

import ro.ase.abstractClasses.Meniu;

public class MeniuCont extends Meniu {
    @Override
    public void afiseazaMeniu() {
        System.out.println("Vă rugăm să alegeți un cont:");
        System.out.println("1. Bibliotecar");
        System.out.println("2. Cititor");
        System.out.println("0. Ieșire");
    }
}
