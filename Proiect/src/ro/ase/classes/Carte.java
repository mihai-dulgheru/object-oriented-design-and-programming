package ro.ase.classes;

import ro.ase.enums.Categorie;
import ro.ase.interfaces.IFisier;
import ro.ase.util.Fisier;
import ro.ase.util.GeneratorISBN;

@SuppressWarnings("StringBufferReplaceableByString")
public class Carte implements IFisier {
    private String isbn;
    private String titlu;
    private String autor;
    private String editura;
    private Integer anAparitie;
    private Integer nrPagini;
    private Categorie categorie;

    public Carte(String titlu, String autor, String editura, Integer anAparitie, Integer nrPagini,
            Categorie categorie) {
        this.isbn = GeneratorISBN.genereazaISBN();
        this.titlu = titlu;
        this.autor = autor;
        this.editura = editura;
        this.anAparitie = anAparitie;
        this.nrPagini = nrPagini;
        this.categorie = categorie;
    }

    public static Carte fromFile(String file) {
        String[] tokens = file.split(Fisier.SEPARATOR);
        Carte carte = new Carte(tokens[1], tokens[2], tokens[3], Integer.parseInt(tokens[4]),
                Integer.parseInt(tokens[5]), Categorie.valueOf(tokens[6]));
        carte.isbn = tokens[0];
        return carte;
    }

    public static int getNrAtribute() {
        return 7;
    }

    public String getIsbn() {
        return isbn;
    }

    public String getTitlu() {
        return titlu;
    }

    public void setTitlu(String titlu) {
        this.titlu = titlu;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public String getEditura() {
        return editura;
    }

    public void setEditura(String editura) {
        this.editura = editura;
    }

    public Integer getAnAparitie() {
        return anAparitie;
    }

    public void setAnAparitie(Integer anAparitie) {
        this.anAparitie = anAparitie;
    }

    public Integer getNrPagini() {
        return nrPagini;
    }

    public void setNrPagini(Integer nrPagini) {
        this.nrPagini = nrPagini;
    }

    public Categorie getCategorie() {
        return categorie;
    }

    public void setCategorie(Categorie categorie) {
        this.categorie = categorie;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Carte{");
        sb.append("isbn='").append(isbn).append('\'');
        sb.append(", titlu='").append(titlu).append('\'');
        sb.append(", autor='").append(autor).append('\'');
        sb.append(", editura='").append(editura).append('\'');
        sb.append(", anAparitie=").append(anAparitie);
        sb.append(", nrPagini=").append(nrPagini);
        sb.append(", categorie=").append(categorie);
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
        String separator = Fisier.SEPARATOR;
        sb.append(isbn).append(separator);
        sb.append(titlu).append(separator);
        sb.append(autor).append(separator);
        sb.append(editura).append(separator);
        sb.append(anAparitie).append(separator);
        sb.append(nrPagini).append(separator);
        sb.append(categorie).append(separator);
        return sb.toString();
    }
}
