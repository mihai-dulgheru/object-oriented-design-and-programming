package ro.ase.seminar8;

public class SortareMain {

    public static void main(String[] args) throws InterruptedException {
        Sortare1 sortare1 = new Sortare1();
        Sortare2 sortare2 = new Sortare2();

        sortare1.setPriority(Thread.MAX_PRIORITY);
        sortare1.join(5_000);
        sortare1.start();
        int i = 0;
        while (sortare1.isAlive()) {
            i++;
        }
        System.out.println("Am terminat sortarea 1 in " + i + " iteratii!");

        sortare2.start();
        i = 0;
        while (sortare2.isAlive()) {
            i++;
        }
        System.out.println("Am terminat sortarea 2 in " + i + " iteratii!");

    }
}
