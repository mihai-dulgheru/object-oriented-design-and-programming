package ro.ase.seminar8;

public class HelloRunnable implements Runnable {
    String[] vector = {"Hello", "from", "thread"};

    public static void main(String[] args) {
        HelloRunnable helloRunnable = new HelloRunnable();
        Thread thread = new Thread(helloRunnable);
        thread.start();
    }

    @Override
    public void run() {
        for (int i = 0; i < vector.length; i++) {
            try {
                Thread.sleep(2000);
                System.out.println(vector[i]);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        System.out.println("Done!");
    }
}
