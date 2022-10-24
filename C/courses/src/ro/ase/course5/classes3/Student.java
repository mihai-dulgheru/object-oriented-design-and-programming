package ro.ase.course5.classes3;

public class Student extends Persoana implements ICanEnrol, ICanSubmit {

    static int nr = 0;
    private final int anStudiu = 1;
    protected int varsta;
    float medie;

    public Student(String id, String nume, int varsta, float medie) {
        super(id, nume);
        this.varsta = varsta;
        this.medie = medie;
        nr++;
    }

    public Student() {
        super();
        this.varsta = 23;
        this.medie = 9.7f;
        nr++;
    }

    public Student(Student s) {
        super(s.id, s.nume);
        this.varsta = s.varsta;
        this.medie = s.medie;
        nr++;
    }

    public static int numarStud() {
        return Student.nr;
    }

    @Override
    public void faceCeva(String tema) {
        System.out.println("O scoala trimite " + tema);
    }

    @Override
    public void inregistreaza() {
        System.out.println("O scoala trimite o aplicatie");
    }

    @Override
    public void trimite() {
        System.out.println("O scoala trimite un raport");
    }

    public int getAnStudiu() {
        return anStudiu;
    }

    public String getNume() {
        return nume;
    }

    public void setNume(String nume) {
        this.nume = nume;
    }

    public int getVarsta() {
        return varsta;
    }

    public void setVarsta(int varsta) {
        this.varsta = varsta;
    }

    public float getMedie() {
        return medie;
    }

    public void setMedie(float medie) {
        this.medie = medie;
    }

    @Override
    public String toString() {
        return "Studentul cu id-ul " + this.id + ", anul de studii " + anStudiu + " si numele " + nume + " are varsta " + varsta
                + " si media " + medie;
    }
}

