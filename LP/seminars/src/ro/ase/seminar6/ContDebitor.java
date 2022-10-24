package ro.ase.seminar6;

public final class ContDebitor extends ContBancar implements IBeneficii {
    public final double BALANTA_MIN = 10.0;

    ContDebitor() {
        this.balanta = BALANTA_MIN;
        this.id = "ContD";
        this.titular = "Anonim";
    }

    @Override
    public void Retragere(double suma) throws ExceptieFonduriInsuficiente {
        if (this.balanta - suma < BALANTA_MIN) {
            throw new ExceptieFonduriInsuficiente();
        } else {
            this.balanta -= suma;
        }
    }

    @Override
    public void adaugaDobanda(float rata, int perioada) {
        this.balanta = this.balanta * (1 + rata * perioada / 100);
    }
}
