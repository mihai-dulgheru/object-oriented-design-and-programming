package ro.ase.classes;

import ro.ase.abstractClasses.Cititor;
import ro.ase.exceptions.ExceptieCititorInexistent;
import ro.ase.interfaces.IImprumut;
import ro.ase.util.Data;
import ro.ase.util.Fisier;

import java.util.*;

public class Imprumut implements IImprumut {
    private final String cnpCititor;
    private final List<String> cartiImprumutate;
    private final Map<String, Date> dataRetur;
    private Date dataImprumut;

    public Imprumut(String cnpCititor) throws ExceptieCititorInexistent {
        if (ListaCititori.getInstance().getCititor(cnpCititor) == null) {
            throw new ExceptieCititorInexistent("Cititorul nu există");
        }
        this.cnpCititor = cnpCititor;
        this.cartiImprumutate = new Vector<>(3);
        this.dataImprumut = new Date();
        this.dataRetur = new HashMap<>();
    }

    public static Imprumut fromFile(String file) {
        String[] tokens = file.split(Fisier.SEPARATOR);
        String cnpCititor = tokens[0];
        Imprumut imprumut = null;
        try {
            imprumut = new Imprumut(cnpCititor);
            int nrCarti = Integer.parseInt(tokens[1]);
            imprumut.cartiImprumutate.addAll(Arrays.asList(tokens).subList(2, nrCarti + 2));
            imprumut.dataImprumut = Data.convertesteData(tokens[2 + nrCarti]);
            for (int i = 3 + nrCarti; i < tokens.length; i += 2) {
                imprumut.dataRetur.put(tokens[i], Data.convertesteData(tokens[i + 1]));
            }
        } catch (ExceptieCititorInexistent e) {
            throw new RuntimeException(e);
        }
        return imprumut;
    }

    public String getCnpCititor() {
        return cnpCititor;
    }

    public List<String> getCartiImprumutate() {
        return cartiImprumutate;
    }

    public Date getDataImprumut() {
        return dataImprumut;
    }

    public Map<String, Date> getDataRetur() {
        return dataRetur;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Imprumut{");
        sb.append("cnpCititor='").append(cnpCititor).append('\'');
        sb.append(", cartiImprumutate=").append(cartiImprumutate);
        sb.append(", dataImprumut=").append(dataImprumut);
        sb.append(", dataRetur=").append(dataRetur);
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
        sb.append(cnpCititor).append(separator);
        sb.append(cartiImprumutate.size()).append(separator);
        for (String carte : cartiImprumutate) {
            sb.append(carte).append(separator);
        }
        sb.append(Data.convertesteData(dataImprumut)).append(separator);
        for (Map.Entry<String, Date> entry : dataRetur.entrySet()) {
            sb.append(entry.getKey()).append(separator);
            sb.append(Data.convertesteData(entry.getValue())).append(separator);
        }
        return sb.toString();
    }

    /**
     * Calculează data de returnare a unei cărți
     *
     * @return data de returnare a cărții
     */
    private Date calculeazaDataRetur() {
        Cititor cititor = ListaCititori.getInstance().getCititor(cnpCititor);
        int nrZile = 0;
        if (cititor instanceof Elev) {
            nrZile = 28;
        } else if (cititor instanceof Student) {
            nrZile = 21;
        } else {
            nrZile = 14;
        }
        return new Date(dataImprumut.getTime() + (long) nrZile * 24 * 60 * 60 * 1000);
    }

    /**
     * Adauga o carte la lista de carti imprumutate
     *
     * @param isbn ISBN-ul cărții care urmează să fie adăugată
     */
    @Override
    public void adaugaCarte(String isbn) {
        if (cartiImprumutate.size() < 3) {
            cartiImprumutate.add(isbn);
            dataRetur.put(isbn, calculeazaDataRetur());
        } else {
            throw new UnsupportedOperationException("Nu se pot împrumuta mai mult de 3 cărți");
        }
    }

    /**
     * Șterge o carte din lista de cărți împrumutate
     *
     * @param isbn ISBN-ul cărții care urmează să fie ștearsă
     */
    @Override
    public void stergeCarte(String isbn) {
        cartiImprumutate.remove(isbn);
        dataRetur.remove(isbn);
    }

    /**
     * Calculează amenzi pentru o carte
     *
     * @param isbn ISBN-ul cărții pentru care se calculează amenzile
     * @return suma de bani care trebuie achitată
     */
    @Override
    public Integer calculeazaAmenzi(String isbn) {
        Date dataRetur = this.dataRetur.get(isbn);
        Date dataCurenta = new Date();
        if (dataCurenta.before(dataRetur)) {
            return 0;
        }
        long diff = dataCurenta.getTime() - dataRetur.getTime();
        long diffDays = diff / (24 * 60 * 60 * 1000);
        return (int) diffDays;
    }
}
