package ro.ase.seminar8;

import java.io.DataOutputStream;
import java.io.IOException;
import java.io.OutputStream;

public class FibonacciProducer extends Thread {

    private final DataOutputStream outputStream;
    private final int noOfElements;

    public FibonacciProducer(OutputStream outputStream, int noOfElements) {
        this.outputStream = new DataOutputStream(outputStream);
        this.noOfElements = noOfElements;
    }

    @Override
    public void run() {
        try {
            int f1 = 1;
            int f2 = 1;

            outputStream.writeInt(f1);
            System.out.println("Producător: " + f1);
            outputStream.writeInt(f2);
            System.out.println("Producător: " + f2);

            for (int i = 2; i < noOfElements; i++) {
                int temp = f2;
                f2 = f1 + f2;
                f1 = temp;

                outputStream.writeInt(f2);
                System.out.println("Producător: " + f2);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
