package ro.ase.classes;

import ro.ase.constants.CaiFisiere;
import ro.ase.enums.Categorie;
import ro.ase.exceptions.ExceptieIncarcareDateDinFisier;
import ro.ase.interfaces.IFisier;
import ro.ase.interfaces.ISalveazaInFisier;
import ro.ase.util.Fisier;

import java.util.HashMap;
import java.util.Map;

public class Biblioteca implements IFisier, ISalveazaInFisier {
    private static Biblioteca instance = null;
    private String nume;
    private String adresa;
    private String nrTelefon;
    private String email;
    private String orar;
    private Map<Categorie, ColectieCarti> colectii;

    private Biblioteca(String nume, String adresa, String nrTelefon, String email, String orar,
            Map<Categorie, ColectieCarti> colectii) {
        this.nume = nume;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        this.email = email;
        this.orar = orar;
        if (colectii != null) {
            this.colectii = colectii;
        } else {
            throw new UnsupportedOperationException("Map-ul de colecții nu poate fi null");
        }
    }

    public static Biblioteca getInstance(String nume, String adresa, String nrTelefon, String email, String orar,
            Map<Categorie, ColectieCarti> colectii) {
        if (instance == null) {
            instance = new Biblioteca(nume, adresa, nrTelefon, email, orar, colectii);
        }
        return instance;
    }

    public static Biblioteca getInstance() {
        if (instance == null) {
            throw new UnsupportedOperationException("Biblioteca nu a fost inițializată");
        }
        return instance;
    }

    public static Biblioteca fromFile(String file) throws ExceptieIncarcareDateDinFisier {
        if (file.isEmpty()) {
            return Biblioteca.getInstance("", "", "", "", "", new HashMap<>());
        }
        try {
            String[] lines = file.split(System.lineSeparator());
            String separator = Fisier.SEPARATOR;
            String[] values = lines[0].split(separator);
            String nume = values[0];
            String adresa = values[1];
            String nrTelefon = values[2];
            String email = values[3];
            String orar = values[4];
            Map<Categorie, ColectieCarti> colectii = new HashMap<>();
            for (int i = 1; i < lines.length; i++) {
                String[] line = lines[i].split(separator);
                Categorie categorie = Categorie.valueOf(line[0]);
                String[] lineWithoutCategorie = new String[line.length - 1];
                System.arraycopy(line, 1, lineWithoutCategorie, 0, line.length - 1);
                ColectieCarti colectieCarti = ColectieCarti.fromFile(String.join(separator, lineWithoutCategorie));
                colectii.put(categorie, colectieCarti);
            }
            return Biblioteca.getInstance(nume, adresa, nrTelefon, email, orar, colectii);
        } catch (Exception e) {
            throw new ExceptieIncarcareDateDinFisier(e.toString());
        }
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getAdresa() {
        return adresa;
    }

    public void setAdresa(String adresa) {
        this.adresa = adresa;
    }

    public String getNrTelefon() {
        return nrTelefon;
    }

    public void setNrTelefon(String nrTelefon) {
        this.nrTelefon = nrTelefon;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getOrar() {
        return orar;
    }

    public void setOrar(String orar) {
        this.orar = orar;
    }

    public Map<Categorie, ColectieCarti> getColectii() {
        return colectii;
    }

    public void setColectii(Map<Categorie, ColectieCarti> colectii) {
        this.colectii = colectii;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Biblioteca{");
        sb.append("nume='").append(nume).append('\'');
        sb.append(", adresa='").append(adresa).append('\'');
        sb.append(", nrTelefon='").append(nrTelefon).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", orar='").append(orar).append('\'');
        sb.append(", colectii=").append(colectii);
        sb.append('}');
        return sb.toString();
    }

    /**
     * Convertește un obiect într-un string ce urmează să fie scris într-un fișier
     *
     * @return stringul ce urmează să fie scris într-un fișier
     */
    @Override
    public String toFile() {
        StringBuilder sb = new StringBuilder();
        String csvSeparator = Fisier.SEPARATOR;
        String lineSeparator = System.lineSeparator();
        sb.append(nume).append(csvSeparator);
        sb.append(adresa).append(csvSeparator);
        sb.append(nrTelefon).append(csvSeparator);
        sb.append(email).append(csvSeparator);
        sb.append(orar).append(lineSeparator);
        for (Map.Entry<Categorie, ColectieCarti> entry : colectii.entrySet()) {
            if (entry.getValue().getCarti().size() > 0) {
                sb.append(entry.getKey()).append(csvSeparator);
                sb.append(entry.getValue().toFile()).append(lineSeparator);
            }
        }
        return sb.toString();
    }

    /**
     * Salvează un obiect într-un fișier
     */
    @Override
    public void salveazaInFisier() {
        if (!nume.isEmpty() && !adresa.isEmpty() && !nrTelefon.isEmpty() && !email.isEmpty() && !orar.isEmpty()
                && !colectii.isEmpty()) {
            Fisier.salveazaInFisier(this, CaiFisiere.BIBLIOTECA);
        }
    }
}
