package ro.ase.seminar6;

public class Vector {

    private final int[] valori;
    private IPrelucrare handler = null;

    public Vector(int n) {
        valori = new int[n];
        for (int i = 0; i < n; i++) {
            valori[i] = i + 1;
        }


    }

    public void addHandler(IPrelucrare h) {
        handler = h;
    }

    public int Prelucreaza(int tip) {
        return switch (tip) {
            case 1 -> handler.suma(this.valori);
            case 2 -> handler.produs(this.valori);
            case 3 -> handler.minim(this.valori);
            case 4 -> handler.maxim(this.valori);
            default -> 0;
        };
    }
}
