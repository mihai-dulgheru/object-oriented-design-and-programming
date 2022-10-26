package ro.ase.seminar7;

public abstract class ContBancar extends Cont {
    protected double balanta;
    protected String titular;
    String id;

    public String getTitular() {
        return titular;
    }

    public void setTitular(String titular) {
        this.titular = titular;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    @Override
    public double getBalanta() {
        return this.balanta;
    }

    @Override
    public void Depunere(double suma) {
        this.balanta += suma;
    }
}
