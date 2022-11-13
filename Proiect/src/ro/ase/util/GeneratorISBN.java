package ro.ase.util;

public class GeneratorISBN {
    public static final String PREFIX_ISBN = "978";
    public static final String COD_TARA = "973";

    public static String genereazaISBN() {
        String isbn = PREFIX_ISBN + "-" + COD_TARA + "-";

        int x1 = 0, x2 = 9999;
        double f = Math.random() / Math.nextDown(1.0);
        int registrantElement = (int) (x1 * (1.0 - f) + x2 * f);
        isbn += String.format("%04d", registrantElement) + "-";

        f = Math.random() / Math.nextDown(1.0);
        x2 = 99;
        int publicationElement = (int) (x1 * (1.0 - f) + x2 * f);
        isbn += String.format("%02d", publicationElement) + "-";

        int checkSum = 0;
        for (int i = 0; i < isbn.length(); i++) {
            if (isbn.charAt(i) != '-') {
                checkSum += Character.getNumericValue(isbn.charAt(i)) * (i % 2 == 0 ? 1 : 3);
            }
        }
        checkSum %= 10;
        isbn += String.valueOf(checkSum);

        return isbn;
    }
}
