package ro.ase.interfaces;

import ro.ase.classes.Carte;
import ro.ase.classes.ColectieCarti;

import java.util.Date;
import java.util.List;

public interface IRaport {
    /**
     * Generează un raport cu toate cărțile scrise de un anumit autor
     *
     * @param carti lista de cărți
     */
    void genereazaRaportAutor(List<? extends Carte> carti);

    /**
     * Generează un raport cu toate cărțile din colecție
     *
     * @param colectie colecția de cărți
     */
    void genereazaRaportColectie(ColectieCarti colectie);

    /**
     * Generează un raport al tuturor cărților care au fost împrumutate la o anumită
     * dată
     *
     * @param carti lista de cărți
     * @param data  data la care au fost împrumutate cărțile
     */
    void genereazaRaportDataImprumut(List<? extends Carte> carti, Date data);
}
