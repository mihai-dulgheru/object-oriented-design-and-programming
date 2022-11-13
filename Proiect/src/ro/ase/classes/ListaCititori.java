package ro.ase.classes;

import ro.ase.abstractClasses.Cititor;
import ro.ase.constants.CaiFisiere;
import ro.ase.exceptions.ExceptieIncarcareDateDinFisier;
import ro.ase.interfaces.IFisier;
import ro.ase.interfaces.ISalveazaInFisier;
import ro.ase.util.Fisier;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ListaCititori implements IFisier, ISalveazaInFisier {
    private static ListaCititori instance = null;
    private final List<Cititor> listaCititori;

    public ListaCititori() {
        listaCititori = new ArrayList<>();
    }

    public static ListaCititori getInstance() {
        if (instance == null) {
            instance = new ListaCititori();
        }
        return instance;
    }

    public static ListaCititori fromFile(String file) throws ExceptieIncarcareDateDinFisier {
        ListaCititori listaCititori = ListaCititori.getInstance();
        if (file.isEmpty()) {
            return listaCititori;
        }
        try {
            String[] lines = file.split(System.lineSeparator());
            String separator = Fisier.SEPARATOR;
            for (String line : lines) {
                String[] values = line.split(separator);
                String tipCititor = values[0];
                String restul = String.join(separator, Arrays.copyOfRange(values, 1, values.length));
                if (tipCititor.equals("Student")) {
                    listaCititori.adaugaCititor(Student.fromFile(restul));
                } else {
                    listaCititori.adaugaCititor(Elev.fromFile(restul));
                }
            }
            return listaCititori;
        } catch (Exception e) {
            throw new ExceptieIncarcareDateDinFisier(e.getMessage());
        }
    }

    public List<Cititor> getListaCititori() {
        return listaCititori;
    }

    @SuppressWarnings("StringBufferReplaceableByString")
    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("ListaCititori{");
        sb.append("listaCititori=").append(listaCititori);
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
        for (Cititor cititor : listaCititori) {
            if (cititor instanceof Student) {
                sb.append("Student").append(csvSeparator);
            } else {
                sb.append("Elev").append(csvSeparator);
            }
            sb.append(cititor.toFile()).append(lineSeparator);
        }
        return sb.toString();
    }

    /**
     * Salvează un obiect într-un fișier
     */
    @Override
    public void salveazaInFisier() {
        if (!listaCititori.isEmpty()) {
            Fisier.salveazaInFisier(this, CaiFisiere.LISTA_CITITORI);
        }
    }

    public void adaugaCititor(Cititor cititor) {
        listaCititori.add(cititor);
    }

    public Cititor getCititor(String cnp) {
        return listaCititori.stream().filter(cititor -> cititor.getCnp().equals(cnp)).findFirst().orElse(null);
    }
}
