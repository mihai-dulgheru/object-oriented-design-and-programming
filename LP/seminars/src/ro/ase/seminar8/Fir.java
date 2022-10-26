package ro.ase.seminar8;

public class Fir extends Thread {

    @Override
    public void run() {
        for (int i = 0; i < 10; i++) {
            System.out.println("Se încearcă o noua retragere...");
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
            Cont.retragere((int) (Math.random() * 500));
        }
    }
}
