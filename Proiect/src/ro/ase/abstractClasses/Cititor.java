package ro.ase.abstractClasses;

import ro.ase.interfaces.IFisier;
import ro.ase.util.Data;
import ro.ase.util.Fisier;

import java.util.Date;

@SuppressWarnings("StringBufferReplaceableByString")
public abstract class Cititor implements IFisier {
    private final String cnp;
    private final Date dataNasterii;
    private String nume;
    private String prenume;
    private String adresa;
    private String nrTelefon;
    private String email;

    public Cititor(String cnp, String nume, String prenume, Date dataNasterii, String adresa, String nrTelefon,
            String email) {
        this.cnp = cnp;
        this.nume = nume;
        this.prenume = prenume;
        this.dataNasterii = dataNasterii;
        this.adresa = adresa;
        this.nrTelefon = nrTelefon;
        this.email = email;
    }

    public String getCnp() {
        return cnp;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public String getPrenume() {
        return prenume;
    }

    public void setPrenume(String prenume) {
        this.prenume = prenume;
    }

    public Date getDataNasterii() {
        return dataNasterii;
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

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Cititor{");
        sb.append("cnp='").append(cnp).append('\'');
        sb.append(", dataNasterii=").append(dataNasterii);
        sb.append(", nume='").append(nume).append('\'');
        sb.append(", prenume='").append(prenume).append('\'');
        sb.append(", adresa='").append(adresa).append('\'');
        sb.append(", nrTelefon='").append(nrTelefon).append('\'');
        sb.append(", email='").append(email).append('\'');
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
        sb.append(cnp).append(separator);
        sb.append(nume).append(separator);
        sb.append(prenume).append(separator);
        sb.append(Data.convertesteData(dataNasterii)).append(separator);
        sb.append(adresa).append(separator);
        sb.append(nrTelefon).append(separator);
        sb.append(email).append(separator);
        return sb.toString();
    }
}
