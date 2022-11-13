package ro.ase.classes;

import ro.ase.constants.CaiFisiere;
import ro.ase.exceptions.ExceptieIncarcareDateDinFisier;
import ro.ase.interfaces.IFisier;
import ro.ase.interfaces.ISalveazaInFisier;
import ro.ase.util.Fisier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class ListaImprumuturi implements IFisier, ISalveazaInFisier {
    private static final Integer capacitate = 10;
    private Imprumut[] listaImprumuturi;
    private Integer nrImprumuturi;

    public ListaImprumuturi() {
        this.listaImprumuturi = new Imprumut[capacitate];
        this.nrImprumuturi = 0;
    }

    public ListaImprumuturi(Integer capacitate) {
        this.listaImprumuturi = new Imprumut[capacitate];
        this.nrImprumuturi = 0;
    }

    public static ListaImprumuturi fromFile(String file) throws ExceptieIncarcareDateDinFisier {
        if (file.isEmpty()) {
            return new ListaImprumuturi();
        }
        try {
            String[] linii = file.split(System.lineSeparator());
            ListaImprumuturi listaImprumuturi = new ListaImprumuturi(linii.length);
            for (String linie : linii) {
                Imprumut imprumut = Imprumut.fromFile(linie);
                listaImprumuturi.adaugaImprumut(imprumut);
            }
            return listaImprumuturi;
        } catch (Exception e) {
            throw new ExceptieIncarcareDateDinFisier(e.getMessage());
        }
    }

    public void adaugaImprumut(Imprumut imprumut) {
        if (this.nrImprumuturi >= this.listaImprumuturi.length) {
            this.listaImprumuturi = Arrays.copyOf(this.listaImprumuturi, this.listaImprumuturi.length + capacitate);
        }
        this.listaImprumuturi[this.nrImprumuturi] = imprumut;
        this.nrImprumuturi++;
    }

    public void stergeImprumut(Imprumut imprumut) {
        for (int i = 0; i < this.nrImprumuturi; i++) {
            if (this.listaImprumuturi[i].equals(imprumut)) {
                for (int j = i; j < this.nrImprumuturi - 1; j++) {
                    this.listaImprumuturi[j] = this.listaImprumuturi[j + 1];
                }
                this.nrImprumuturi--;
                break;
            }
        }
    }

    public Imprumut[] getListaImprumuturi() {
        return listaImprumuturi;
    }

    public Integer getNrImprumuturi() {
        return nrImprumuturi;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ListaImprumuturi{");
        sb.append("listaImprumuturi=").append(Arrays.toString(listaImprumuturi));
        sb.append(", nrImprumuturi=").append(nrImprumuturi);
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
        String separator = System.lineSeparator();
        for (int i = 0; i < this.nrImprumuturi; i++) {
            sb.append(this.listaImprumuturi[i].toFile()).append(separator);
        }
        return sb.toString();
    }

    /**
     * Salvează un obiect într-un fișier
     */
    @Override
    public void salveazaInFisier() {
        Fisier.salveazaInFisier(this, CaiFisiere.LISTA_IMPRUMUTURI);
    }

    public List<Carte> getCartiImprumutate(Date dataCautata) {
        List<Carte> cartiImprumutate = new ArrayList<>();
        ListaCarti listaCarti = ListaCarti.getInstance();
        for (int i = 0; i < this.nrImprumuturi; i++) {
            if (this.listaImprumuturi[i].getDataImprumut().equals(dataCautata)) {
                List<String> carti = this.listaImprumuturi[i].getCartiImprumutate();
                for (String carte : carti) {
                    cartiImprumutate.add(listaCarti.cautaCarte(carte));
                }
            }
        }
        return cartiImprumutate;
    }

    public boolean esteImprumutata(String isbn) {
        for (int i = 0; i < this.nrImprumuturi; i++) {
            List<String> carti = this.listaImprumuturi[i].getCartiImprumutate();
            for (String carte : carti) {
                if (carte.equals(isbn)) {
                    return true;
                }
            }
        }
        return false;
    }
}
