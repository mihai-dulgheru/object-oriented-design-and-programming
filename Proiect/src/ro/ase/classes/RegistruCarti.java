package ro.ase.classes;

import ro.ase.constants.CaiFisiere;
import ro.ase.exceptions.ExceptieIncarcareDateDinFisier;
import ro.ase.interfaces.IRegistruCarti;
import ro.ase.util.Fisier;

import java.util.HashMap;
import java.util.Map;

public class RegistruCarti implements IRegistruCarti {
    private static RegistruCarti instance = null;
    private final Map<String, Integer> registru;

    private RegistruCarti() {
        registru = new HashMap<>();
    }

    public static RegistruCarti getInstance() {
        if (instance == null) {
            instance = new RegistruCarti();
        }
        return instance;
    }

    public static RegistruCarti fromFile(String file) throws ExceptieIncarcareDateDinFisier {
        RegistruCarti registruCarti = RegistruCarti.getInstance();
        if (file.isEmpty()) {
            return registruCarti;
        }
        try {
            String[] lines = file.split(System.lineSeparator());
            for (String line : lines) {
                String[] values = line.split(Fisier.SEPARATOR);
                registruCarti.adaugaCarte(values[0], Integer.parseInt(values[1]));
            }
            return registruCarti;
        } catch (Exception e) {
            throw new ExceptieIncarcareDateDinFisier(e.getMessage());
        }
    }

    public Map<String, Integer> getRegistru() {
        return registru;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("RegistruCarti{");
        sb.append("registru=").append(registru);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Adaugă o carte în registru
     *
     * @param isbn        ISBN-ul cărții care urmează să fie adăugată
     * @param nrExemplare numărul de exemplare ale cărții care urmează să fie
     *                    adăugate
     */
    @Override
    public void adaugaCarte(String isbn, int nrExemplare) {
        if (registru.containsKey(isbn)) {
            registru.put(isbn, registru.get(isbn) + nrExemplare);
        } else {
            registru.put(isbn, nrExemplare);
        }
    }

    /**
     * Șterge o carte din registru
     *
     * @param isbn ISBN-ul cărții care urmează să fie ștearsă
     */
    @Override
    public void stergeCarte(String isbn) {
        registru.remove(isbn);
    }

    /**
     * Verifică dacă o carte este disponibilă
     *
     * @param isbn ISBN-ul cărții care urmează să fie verificată
     * @return true dacă este disponibilă, false altfel
     */
    @Override
    public Boolean verificaDisponibilitate(String isbn) {
        return registru.containsKey(isbn) && registru.get(isbn) > 0;
    }

    /**
     * Modifică numărul de exemplare disponibile ale unei cărți
     *
     * @param isbn ISBN-ul cărții
     */
    @Override
    public void imprumutaCarte(String isbn) {
        if (registru.containsKey(isbn)) {
            registru.put(isbn, registru.get(isbn) - 1);
        }
    }

    /**
     * Modifică numărul de exemplare disponibile ale unei cărți
     *
     * @param isbn ISBN-ul cărții
     */
    @Override
    public void returneazaCarte(String isbn) {
        if (registru.containsKey(isbn)) {
            registru.put(isbn, registru.get(isbn) + 1);
        }
    }

    /**
     * Convertește un obiect într-un string ce urmează să fie scris într-un fișier
     *
     * @return stringul ce urmează să fie scris într-un fișier
     */
    @Override
    public String toFile() {
        StringBuilder stringBuilder = new StringBuilder();
        for (Map.Entry<String, Integer> entry : registru.entrySet()) {
            stringBuilder.append(entry.getKey()).append(Fisier.SEPARATOR).append(entry.getValue())
                    .append(System.lineSeparator());
        }
        return stringBuilder.toString();
    }

    /**
     * Salvează un obiect într-un fișier
     */
    @Override
    public void salveazaInFisier() {
        Fisier.salveazaInFisier(this, CaiFisiere.REGISTRU_CARTI);
    }
}
