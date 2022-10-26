package ro.ase.seminar7;

public final class ContCreditor extends ContBancar {
    public final double MAX_CREDIT = 1000.0;

    ContCreditor() {
        this.balanta = 0;
        this.id = "ContC";
        this.titular = "Anonim";
    }

    @Override
    public void Retragere(double suma) throws ExceptieFonduriInsuficiente {
        if (this.balanta - suma < MAX_CREDIT) {
            throw new ExceptieFonduriInsuficiente();
        } else {
            balanta -= suma;
        }
    }
}
