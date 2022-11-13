package ro.ase.classes;

import ro.ase.interfaces.IColectieCarti;
import ro.ase.util.Fisier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ColectieCarti implements IColectieCarti {
    private final List<String> carti;
    private Integer nrCarti;

    public ColectieCarti(List<String> carti) {
        if (carti != null) {
            this.carti = carti;
            this.nrCarti = carti.size();
        } else {
            throw new UnsupportedOperationException("Lista de cărți nu poate fi null");
        }
    }

    public static ColectieCarti fromFile(String file) {
        String separator = Fisier.SEPARATOR;
        String[] tokens = file.split(separator);
        List<String> carti = new ArrayList<>(Arrays.asList(tokens));
        return new ColectieCarti(carti);
    }

    public List<String> getCarti() {
        return carti;
    }

    public Integer getNrCarti() {
        return nrCarti;
    }

    public void setNrCarti(Integer nrCarti) {
        this.nrCarti = nrCarti;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ColectieCarti{");
        sb.append("carti=").append(carti);
        sb.append(", nrCarti=").append(nrCarti);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Adaugă o carte în colecție
     *
     * @param isbn ISBN-ul cărții care urmează să fie adăugată
     */
    @Override
    public void adaugaCarte(String isbn) {
        carti.add(isbn);
    }

    /**
     * Șterge o carte din colecție
     *
     * @param isbn ISBN-ul cărții care urmează să fie ștearsă
     */
    @Override
    public void stergeCarte(String isbn) {
        if (isbn != null) {
            carti.remove(isbn);
        } else {
            throw new UnsupportedOperationException("ISBN-ul nu poate fi null");
        }
    }

    /**
     * Convertește un obiect într-un string ce urmează să fie scris într-un fișier
     *
     * @return stringul ce urmează să fie scris într-un fișier
     */
    @Override
    public String toFile() {
        StringBuilder sb = new StringBuilder();
        String separator = Fisier.SEPARATOR;
        for (String isbn : carti) {
            sb.append(isbn).append(separator);
        }
        return sb.toString();
    }
}
