package ro.ase.seminar8;

import java.io.DataInputStream;
import java.io.IOException;
import java.io.InputStream;

public class FibonacciConsumator extends Thread {
    private final DataInputStream inputStream;

    public FibonacciConsumator(InputStream inputStream) {
        this.inputStream = new DataInputStream(inputStream);
    }

    @Override
    public void run() {
        try {
            while (true) {
                System.out.println("Consumator: " + inputStream.readInt());
            }
        } catch (IOException e) {
            if (e.getMessage().equals("Pipe broken") || e.getMessage().equals("Write end dead")) {
                return;
            }
            e.printStackTrace();
        }
    }
}
