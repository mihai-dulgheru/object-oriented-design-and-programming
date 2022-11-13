package ro.ase.classes;

import ro.ase.enums.Categorie;
import ro.ase.interfaces.IRaport;
import ro.ase.util.Data;
import ro.ase.util.Fisier;

import java.util.Date;
import java.util.List;

public class Raport implements IRaport {
    /**
     * Generează un raport cu toate cărțile scrise de un anumit autor
     *
     * @param carti lista de cărți
     */
    @Override
    public void genereazaRaportAutor(List<? extends Carte> carti) {
        StringBuilder raport = new StringBuilder();
        String[] antet = { "ISBN", "Titlu", "Editură", "An apariție", "Număr pagini", "Categorie" };
        int[] lungimi = lungimiMaximeColoaneRaportAutor(antet, carti);
        int length = antet.length;
        String lineSeparator = System.lineSeparator();
        raport.append("| ");
        for (int i = 0; i < length - 1; i++) {
            raport.append(antet[i]).append(" ".repeat(lungimi[i] - antet[i].length())).append(" | ");
        }
        raport.append(antet[length - 1]).append(" ".repeat(lungimi[length - 1] - antet[length - 1].length()))
                .append(" |").append(lineSeparator);
        raport.append("| ");
        for (int i = 0; i < length - 1; i++) {
            raport.append("-".repeat(lungimi[i])).append(" | ");
        }
        raport.append("-".repeat(lungimi[length - 1])).append(" |").append(lineSeparator);
        for (Carte carte : carti) {
            raport.append("| ")
                    .append(carte.getIsbn()).append(" ".repeat(lungimi[0] - carte.getIsbn().length())).append(" | ")
                    .append(carte.getTitlu()).append(" ".repeat(lungimi[1] - carte.getTitlu().length())).append(" | ")
                    .append(carte.getEditura()).append(" ".repeat(lungimi[2] - carte.getEditura().length()))
                    .append(" | ")
                    .append(carte.getAnAparitie())
                    .append(" ".repeat(lungimi[3] - String.valueOf(carte.getAnAparitie()).length())).append(" | ")
                    .append(carte.getNrPagini())
                    .append(" ".repeat(lungimi[4] - String.valueOf(carte.getNrPagini()).length())).append(" | ")
                    .append(carte.getCategorie().name())
                    .append(" ".repeat(lungimi[5] - carte.getCategorie().name().length()))
                    .append(" |").append(lineSeparator);
        }
        String autor = carti.get(0).getAutor();
        String numeFisier = String.format("data/reports/Raport autor %s%s", autor, Fisier.EXTENSIE);
        String continut = raport.toString();
        Fisier.genereazaRaport(numeFisier, continut);
        System.out.println("Raportul a fost generat cu succes!");
        System.out.println("Calea către raport: ~/" + numeFisier);
        System.out.println("Conținutul raportului: ");
        System.out.println(continut);
    }

    /**
     * Generează un raport cu toate cărțile din colecție
     *
     * @param colectie colecția de cărți
     */
    @Override
    public void genereazaRaportColectie(ColectieCarti colectie) {
        String lineSeparator = System.lineSeparator();
        if (colectie.getNrCarti() == 0) {
            System.err.println("Nu există cărți în colecție!" + lineSeparator);
            return;
        }
        List<String> isbn = colectie.getCarti();
        ListaCarti lista = ListaCarti.getInstance();
        Categorie categorie = lista.cautaCarte(isbn.get(0)).getCategorie();
        List<Carte> listaCarti = lista.cautaCartiDupaCategorie(categorie);

        StringBuilder raport = new StringBuilder();
        String[] antet = { "ISBN", "Titlu", "Autor", "Editură", "An apariție", "Număr pagini" };
        int[] lungimi = lungimiMaximeColoaneRaportColectie(antet, listaCarti);
        int length = antet.length;
        raport.append("| ");
        for (int i = 0; i < length - 1; i++) {
            raport.append(antet[i]).append(" ".repeat(lungimi[i] - antet[i].length())).append(" | ");
        }
        raport.append(antet[length - 1]).append(" ".repeat(lungimi[length - 1] - antet[length - 1].length()))
                .append(" |").append(lineSeparator);
        raport.append("| ");
        for (int i = 0; i < length - 1; i++) {
            raport.append("-".repeat(lungimi[i])).append(" | ");
        }
        raport.append("-".repeat(lungimi[length - 1])).append(" |").append(lineSeparator);
        for (Carte carte : listaCarti) {
            raport.append("| ")
                    .append(carte.getIsbn()).append(" ".repeat(lungimi[0] - carte.getIsbn().length())).append(" | ")
                    .append(carte.getTitlu()).append(" ".repeat(lungimi[1] - carte.getTitlu().length())).append(" | ")
                    .append(carte.getAutor()).append(" ".repeat(lungimi[2] - carte.getAutor().length())).append(" | ")
                    .append(carte.getEditura()).append(" ".repeat(lungimi[3] - carte.getEditura().length()))
                    .append(" | ")
                    .append(carte.getAnAparitie())
                    .append(" ".repeat(lungimi[4] - String.valueOf(carte.getAnAparitie()).length())).append(" | ")
                    .append(carte.getNrPagini())
                    .append(" ".repeat(lungimi[5] - String.valueOf(carte.getNrPagini()).length()))
                    .append(" |").append(lineSeparator);
        }
        String numeFisier = String.format("data/reports/Raport colecție %s%s", categorie, Fisier.EXTENSIE);
        String continut = raport.toString();
        Fisier.genereazaRaport(numeFisier, continut);
        System.out.println("Raportul a fost generat cu succes!");
        System.out.println("Calea către raport: ~/" + numeFisier);
        System.out.println("Conținutul raportului: ");
        System.out.println(continut);
    }

    /**
     * Generează un raport al tuturor cărților care au fost împrumutate la o anumită
     * dată
     *
     * @param carti lista de cărți
     * @param data  data la care au fost împrumutate cărțile
     */
    @Override
    public void genereazaRaportDataImprumut(List<? extends Carte> carti, Date data) {
        StringBuilder raport = new StringBuilder();
        String[] antet = { "ISBN", "Titlu", "Autor", "Editură", "An apariție", "Număr pagini", "Categorie" };
        int[] lungimi = lungimiMaximeColoaneRaportData(antet, carti);
        int length = antet.length;
        String lineSeparator = System.lineSeparator();
        raport.append("| ");
        for (int i = 0; i < length - 1; i++) {
            raport.append(antet[i]).append(" ".repeat(lungimi[i] - antet[i].length())).append(" | ");
        }
        raport.append(antet[length - 1]).append(" ".repeat(lungimi[length - 1] - antet[length - 1].length()))
                .append(" |").append(lineSeparator);
        raport.append("| ");
        for (int i = 0; i < length - 1; i++) {
            raport.append("-".repeat(lungimi[i])).append(" | ");
        }
        raport.append("-".repeat(lungimi[length - 1])).append(" |").append(lineSeparator);
        for (Carte carte : carti) {
            raport.append("| ")
                    .append(carte.getIsbn()).append(" ".repeat(lungimi[0] - carte.getIsbn().length())).append(" | ")
                    .append(carte.getTitlu()).append(" ".repeat(lungimi[1] - carte.getTitlu().length())).append(" | ")
                    .append(carte.getAutor()).append(" ".repeat(lungimi[2] - carte.getAutor().length())).append(" | ")
                    .append(carte.getEditura()).append(" ".repeat(lungimi[3] - carte.getEditura().length()))
                    .append(" | ")
                    .append(carte.getAnAparitie())
                    .append(" ".repeat(lungimi[4] - String.valueOf(carte.getAnAparitie()).length())).append(" | ")
                    .append(carte.getNrPagini())
                    .append(" ".repeat(lungimi[5] - String.valueOf(carte.getNrPagini()).length())).append(" | ")
                    .append(carte.getCategorie().name())
                    .append(" ".repeat(lungimi[6] - carte.getCategorie().name().length()))
                    .append(" |").append(lineSeparator);
        }
        String numeFisier = String.format("data/reports/Raport dată împrumut %s%s",
                Data.convertesteData(data, "dd-MM-yyyy"), Fisier.EXTENSIE);
        String continut = raport.toString();
        Fisier.genereazaRaport(numeFisier, continut);
        System.out.println("Raportul a fost generat cu succes!");
        System.out.println("Calea către raport: ~/" + numeFisier);
        System.out.println("Conținutul raportului: ");
        System.out.println(continut);
    }

    private int[] lungimiMaximeColoaneRaportAutor(String[] antet, List<? extends Carte> carti) {
        int[] lungimi = new int[antet.length];
        for (int i = 0; i < antet.length; i++) {
            lungimi[i] = antet[i].length();
        }
        lungimi[0] = 17;
        for (Carte carte : carti) {
            if (carte.getTitlu().length() > lungimi[1]) {
                lungimi[1] = carte.getTitlu().length();
            }
            if (carte.getEditura().length() > lungimi[2]) {
                lungimi[2] = carte.getEditura().length();
            }
            if (String.valueOf(carte.getAnAparitie()).length() > lungimi[3]) {
                lungimi[3] = String.valueOf(carte.getAnAparitie()).length();
            }
            if (String.valueOf(carte.getNrPagini()).length() > lungimi[4]) {
                lungimi[4] = String.valueOf(carte.getNrPagini()).length();
            }
            if (carte.getCategorie().name().length() > lungimi[5]) {
                lungimi[5] = carte.getCategorie().name().length();
            }
        }
        return lungimi;
    }

    private int[] lungimiMaximeColoaneRaportColectie(String[] antet, List<? extends Carte> carti) {
        int[] lungimi = new int[antet.length];
        for (int i = 0; i < antet.length; i++) {
            lungimi[i] = antet[i].length();
        }
        lungimi[0] = 17;
        for (Carte carte : carti) {
            if (carte.getTitlu().length() > lungimi[1]) {
                lungimi[1] = carte.getTitlu().length();
            }
            if (carte.getAutor().length() > lungimi[2]) {
                lungimi[2] = carte.getAutor().length();
            }
            if (carte.getEditura().length() > lungimi[3]) {
                lungimi[3] = carte.getEditura().length();
            }
            if (String.valueOf(carte.getAnAparitie()).length() > lungimi[4]) {
                lungimi[4] = String.valueOf(carte.getAnAparitie()).length();
            }
            if (String.valueOf(carte.getNrPagini()).length() > lungimi[5]) {
                lungimi[5] = String.valueOf(carte.getNrPagini()).length();
            }
        }
        return lungimi;
    }

    private int[] lungimiMaximeColoaneRaportData(String[] antet, List<? extends Carte> carti) {
        int[] lungimi = new int[antet.length];
        for (int i = 0; i < antet.length; i++) {
            lungimi[i] = antet[i].length();
        }
        lungimi[0] = 17;
        for (Carte carte : carti) {
            if (carte.getTitlu().length() > lungimi[1]) {
                lungimi[1] = carte.getTitlu().length();
            }
            if (carte.getAutor().length() > lungimi[2]) {
                lungimi[2] = carte.getAutor().length();
            }
            if (carte.getEditura().length() > lungimi[3]) {
                lungimi[3] = carte.getEditura().length();
            }
            if (String.valueOf(carte.getAnAparitie()).length() > lungimi[4]) {
                lungimi[4] = String.valueOf(carte.getAnAparitie()).length();
            }
            if (String.valueOf(carte.getNrPagini()).length() > lungimi[5]) {
                lungimi[5] = String.valueOf(carte.getNrPagini()).length();
            }
            if (carte.getCategorie().name().length() > lungimi[6]) {
                lungimi[6] = carte.getCategorie().name().length();
            }
        }
        return lungimi;
    }
}
