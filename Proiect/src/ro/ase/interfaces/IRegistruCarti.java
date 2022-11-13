package ro.ase.interfaces;

public interface IRegistruCarti extends IFisier, ISalveazaInFisier {
    /**
     * Adaugă o carte în registru
     *
     * @param isbn        ISBN-ul cărții care urmează să fie adăugată
     * @param nrExemplare numărul de exemplare ale cărții care urmează să fie
     *                    adăugate
     */
    void adaugaCarte(String isbn, int nrExemplare);

    /**
     * Șterge o carte din registru
     *
     * @param isbn ISBN-ul cărții care urmează să fie ștearsă
     */
    void stergeCarte(String isbn);

    /**
     * Verifică dacă o carte este disponibilă
     *
     * @param isbn ISBN-ul cărții care urmează să fie verificată
     * @return true dacă este disponibilă, false altfel
     */
    Boolean verificaDisponibilitate(String isbn);

    /**
     * Modifică numărul de exemplare disponibile ale unei cărți
     *
     * @param isbn ISBN-ul cărții
     */
    void imprumutaCarte(String isbn);

    /**
     * Modifică numărul de exemplare disponibile ale unei cărți
     *
     * @param isbn ISBN-ul cărții
     */
    void returneazaCarte(String isbn);
}
