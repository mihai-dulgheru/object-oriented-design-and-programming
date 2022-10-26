package ro.ase.seminar8;

import java.util.Arrays;

public class Sortare2 extends Thread {
    public int[] vector = new int[50];

    @Override
    public void run() {
        for (int i = 0; i < vector.length; i++) {
            vector[i] = (int) (Math.random() * (100 + i));
        }

        Arrays.sort(vector);

        for (int j : vector) {
            System.out.println("Vector 2: " + j);
        }
        System.out.println("Am terminat sortarea 2!");
    }
}
