package ro.ase.classes;

import ro.ase.abstractClasses.Meniu;

public class MeniuCititor extends Meniu {
    @Override
    public void afiseazaMeniu() {
        System.out.println();
        System.out.println("Meniu Cititor");
        System.out.println("1. Înregistrează-te");
        System.out.println("2. Împrumută o carte");
        System.out.println("3. Returnează o carte");
        System.out.println("0. Ieșire");
    }
}
