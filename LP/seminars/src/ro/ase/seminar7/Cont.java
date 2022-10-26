package ro.ase.seminar7;

public abstract class Cont {
    public abstract void Depunere(double suma);

    public abstract void Retragere(double suma) throws ExceptieFonduriInsuficiente;

    public abstract double getBalanta();

    public void transfer(double suma, Cont contDestinatie) throws ExceptieFonduriInsuficiente, ExceptieTransferIlegal {
        if (contDestinatie == this) {
            ExceptieTransferIlegal exceptieTransferIlegal = new ExceptieTransferIlegal();
            throw exceptieTransferIlegal;
        } else {
            Retragere(suma);
            contDestinatie.Depunere(suma);
        }
    }

}
