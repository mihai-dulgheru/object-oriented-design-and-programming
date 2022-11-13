package ro.ase.interfaces;

public interface IColectieCarti extends IFisier {
    /**
     * Adaugă o carte în colecție
     *
     * @param isbn ISBN-ul cărții care urmează să fie adăugată
     */
    void adaugaCarte(String isbn);

    /**
     * Șterge o carte din colecție
     *
     * @param isbn ISBN-ul cărții care urmează să fie ștearsă
     */
    void stergeCarte(String isbn);
}
