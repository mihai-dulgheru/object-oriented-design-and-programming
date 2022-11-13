package ro.ase.interfaces;

public interface IImprumut extends IFisier {
    /**
     * Adauga o carte la lista de carti imprumutate
     *
     * @param isbn ISBN-ul cărții care urmează să fie adăugată
     */
    void adaugaCarte(String isbn);

    /**
     * Șterge o carte din lista de cărți împrumutate
     *
     * @param isbn ISBN-ul cărții care urmează să fie ștearsă
     */
    void stergeCarte(String isbn);

    /**
     * Calculează amenzi pentru o carte
     *
     * @param isbn ISBN-ul cărții pentru care se calculează amenzile
     * @return suma de bani care trebuie achitată
     */
    Integer calculeazaAmenzi(String isbn);
}
