package ro.ase.util;

import ro.ase.interfaces.IFisier;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import static java.nio.file.StandardOpenOption.CREATE;
import static java.nio.file.StandardOpenOption.WRITE;

@SuppressWarnings("ThrowablePrintedToSystemOut")
public class Fisier {
    public static final String SEPARATOR = ";";
    public static final String EXTENSIE = ".txt";

    public static void genereazaRaport(String numeFisier, String continut) {
        byte[] data = continut.getBytes();
        Path p = Paths.get(numeFisier);

        try {
            Files.delete(p);
        } catch (IOException ignored) {
        }

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, WRITE))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static void salveazaInFisier(IFisier obiect, String caleFisier) {
        String s = obiect.toFile();
        byte[] data = s.getBytes();
        Path p = Paths.get(caleFisier);

        try {
            Files.delete(p);
        } catch (IOException ignored) {
        }

        try (OutputStream out = new BufferedOutputStream(Files.newOutputStream(p, CREATE, WRITE))) {
            out.write(data, 0, data.length);
        } catch (IOException x) {
            System.err.println(x);
        }
    }

    public static String citesteDinFisier(String caleFisier) {
        Path file = Paths.get(caleFisier);

        StringBuilder sb = new StringBuilder();
        String line = null;
        try (InputStream in = Files.newInputStream(file);
                BufferedReader reader = new BufferedReader(new InputStreamReader(in))) {
            while ((line = reader.readLine()) != null) {
                sb.append(line);
                sb.append(System.lineSeparator());
            }
        } catch (IOException x) {
            System.err.println(x);
        }
        return sb.toString();
    }
}
