package ro.ase.classes;

import ro.ase.abstractClasses.Cititor;
import ro.ase.util.Data;
import ro.ase.util.Fisier;

import java.util.Date;

public class Student extends Cititor {
    public Student(String cnp, String nume, String prenume, Date dataNasterii, String adresa, String nrTelefon,
            String email) {
        super(cnp, nume, prenume, dataNasterii, adresa, nrTelefon, email);
    }

    public static Student fromFile(String file) {
        String[] values = file.split(Fisier.SEPARATOR);
        return new Student(values[0], values[1], values[2], Data.convertesteData(values[3]), values[4], values[5],
                values[6]);
    }
}
