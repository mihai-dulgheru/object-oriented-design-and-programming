package ro.ase.seminar8;

import java.io.IOException;
import java.io.PipedInputStream;
import java.io.PipedOutputStream;

public class FibonacciMain {

    public static void main(String[] args) {
        try {
            PipedOutputStream pos = new PipedOutputStream();
            PipedInputStream pis = new PipedInputStream(pos);

            FibonacciProducer fp = new FibonacciProducer(pos, 10);
            FibonacciConsumator fc = new FibonacciConsumator(pis);
            fp.start();
            fc.start();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

}
