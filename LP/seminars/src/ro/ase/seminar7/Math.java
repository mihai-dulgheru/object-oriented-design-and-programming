package ro.ase.seminar7;

import static java.lang.Math.max;
import static java.lang.Math.min;

public class Math implements IPrelucrare {
    @Override
    public int suma(int[] vector) {
        int sum = 0;
        for (int element : vector) {
            sum += element;
        }
        return sum;
    }

    @Override
    public int produs(int[] vector) {
        int prod = 1;
        for (int element : vector) {
            prod *= element;
        }
        return prod;
    }

    @Override
    public int minim(int[] vector) {
        int min = Integer.MAX_VALUE;
        for (int element : vector) {
            min = min(element, min);
        }
        return min;
    }

    @Override
    public int maxim(int[] vector) {
        int max = Integer.MIN_VALUE;
        for (int element : vector) {
            max = max(element, max);
        }
        return max;
    }
}
