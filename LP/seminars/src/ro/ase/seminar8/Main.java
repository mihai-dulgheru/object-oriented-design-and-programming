package ro.ase.seminar8;

public class Main {

    public static void main(String[] args) {
        Fir fir1 = new Fir();
        Fir fir2 = new Fir();

        fir1.start();
        System.out.println(fir1.getId());
        fir2.start();
        System.out.println(fir2.getId());
    }
}
