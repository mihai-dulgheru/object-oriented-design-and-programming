package ro.ase.classes;

import ro.ase.constants.CaiFisiere;
import ro.ase.enums.Categorie;
import ro.ase.exceptions.ExceptieIncarcareDateDinFisier;
import ro.ase.interfaces.IListaCarti;
import ro.ase.interfaces.ISalveazaInFisier;
import ro.ase.util.Fisier;

import java.util.ArrayList;
import java.util.List;

public class ListaCarti implements IListaCarti, ISalveazaInFisier {
    private static ListaCarti instance = null;
    private List<Carte> listaCarti;

    private ListaCarti() {
        listaCarti = new ArrayList<>();
    }

    public static ListaCarti getInstance() {
        if (instance == null) {
            instance = new ListaCarti();
        }
        return instance;
    }

    public static ListaCarti fromFile(String file) throws ExceptieIncarcareDateDinFisier {
        ListaCarti listaCarti = ListaCarti.getInstance();
        if (file.isEmpty()) {
            return listaCarti;
        }
        try {
            String[] lines = file.split(System.lineSeparator());
            for (String line : lines) {
                listaCarti.adaugaCarte(Carte.fromFile(line));
            }
            return listaCarti;
        } catch (Exception e) {
            throw new ExceptieIncarcareDateDinFisier(e.getMessage());
        }
    }

    public List<Carte> getListaCarti() {
        return listaCarti;
    }

    public void setListaCarti(List<Carte> listaCarti) {
        this.listaCarti = listaCarti;
    }

    /**
     * Adaugă o carte în colecție
     *
     * @param carte cartea care urmează să fie adăugată
     */
    @Override
    public void adaugaCarte(Carte carte) {
        if (carte != null) {
            listaCarti.add(carte);
        } else {
            throw new UnsupportedOperationException("Cartea nu poate fi null");
        }
    }

    /**
     * Șterge o carte din colecție
     *
     * @param carte cartea care urmează să fie ștearsă
     */
    @Override
    public void stergeCarte(Carte carte) {
        if (carte != null) {
            listaCarti.remove(carte);
        } else {
            throw new UnsupportedOperationException("Cartea nu poate fi null");
        }
    }

    /**
     * Caută o carte în colecție
     *
     * @param isbn ISBN-ul cărții care urmează să fie căutată
     * @return cartea căutată
     */
    @Override
    public Carte cautaCarte(String isbn) {
        return listaCarti.stream().filter(carte -> carte.getIsbn().equals(isbn)).findFirst().orElse(null);
    }

    /**
     * Returnează o listă de cărți scrise de un anumit autor
     *
     * @param autor autorul cărților care urmează să fie returnate
     * @return lista de cărți scrise de autorul dat
     */
    @Override
    public List<Carte> getCarti(String autor) {
        return listaCarti.stream().filter(carte -> carte.getAutor().equals(autor)).toList();
    }

    /**
     * Afișează toate cărțile din colecție
     *
     * @param categorie categoria cărților care urmează să fie afișate
     * @return lista de cărți din categoria dată
     */
    @Override
    public List<Carte> cautaCartiDupaCategorie(Categorie categorie) {
        return listaCarti.stream().filter(carte -> carte.getCategorie().equals(categorie)).toList();
    }

    /**
     * Convertește un obiect într-un string ce urmează să fie scris într-un fișier
     *
     * @return stringul ce urmează să fie scris într-un fișier
     */
    @Override
    public String toFile() {
        StringBuilder sb = new StringBuilder();
        String separator = System.lineSeparator();
        for (Carte carte : listaCarti) {
            sb.append(carte.toFile()).append(separator);
        }
        return sb.toString();
    }

    /**
     * Salvează un obiect într-un fișier
     */
    @Override
    public void salveazaInFisier() {
        Fisier.salveazaInFisier(this, CaiFisiere.LISTA_CARTI);
    }
}
