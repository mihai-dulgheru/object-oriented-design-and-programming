package ro.ase.interfaces;

import ro.ase.classes.Carte;
import ro.ase.enums.Categorie;

import java.util.List;

public interface IListaCarti extends IFisier {
    /**
     * Adaugă o carte în colecție
     *
     * @param carte cartea care urmează să fie adăugată
     */
    void adaugaCarte(Carte carte);

    /**
     * Șterge o carte din colecție
     *
     * @param carte cartea care urmează să fie ștearsă
     */
    void stergeCarte(Carte carte);

    /**
     * Caută o carte în colecție
     *
     * @param isbn ISBN-ul cărții care urmează să fie căutată
     * @return cartea căutată
     */
    Carte cautaCarte(String isbn);

    /**
     * Returnează o listă de cărți scrise de un anumit autor
     *
     * @param autor autorul cărților care urmează să fie returnate
     * @return lista de cărți scrise de autorul dat
     */
    List<Carte> getCarti(String autor);

    /**
     * Afișează toate cărțile din colecție
     *
     * @param categorie categoria cărților care urmează să fie afișate
     * @return lista de cărți din categoria dată
     */
    List<Carte> cautaCartiDupaCategorie(Categorie categorie);
}
