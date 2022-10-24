package ro.ase.seminar1.exercitiu;

import java.util.Arrays;
import java.util.Date;

public class Curs {
    private String profesor;
    private String disciplina;
    private Date dataSustinere;
    private Integer anStudiu;
    private Facultate facultate;
    private Integer[] punctaje;

    public Curs(String profesor, String disciplina, Date dataSustinere, Integer anStudiu, Facultate facultate, Integer[] punctaje) throws Exception {
        this.profesor = profesor;
        this.disciplina = disciplina;
        this.dataSustinere = dataSustinere;
        setAnStudiu(anStudiu);
        this.facultate = facultate;
        this.punctaje = Arrays.copyOf(punctaje, punctaje.length);
    }

    public Curs() throws Exception {
        this("", "", new Date(), 1, Facultate.CSIE, new Integer[]{10});
    }

    public String getProfesor() {
        return profesor;
    }

    public void setProfesor(String profesor) {
        this.profesor = profesor;
    }

    public String getDisciplina() {
        return disciplina;
    }

    public void setDisciplina(String disciplina) {
        this.disciplina = disciplina;
    }

    public Date getDataSustinere() {
        return dataSustinere;
    }

    public void setDataSustinere(Date dataSustinere) {
        this.dataSustinere = dataSustinere;
    }

    public Integer getAnStudiu() {
        return anStudiu;
    }

    public void setAnStudiu(Integer anStudiu) throws Exception {
        if (anStudiu < 0 || anStudiu > 3) {
            throw new Exception("An de studiu invalid");
        }
        this.anStudiu = anStudiu;
    }

    public Facultate getFacultate() {
        return facultate;
    }

    public void setFacultate(Facultate facultate) {
        this.facultate = facultate;
    }

    public Integer[] getPunctaje() {
        return punctaje;
    }

    public void setPunctaje(Integer[] punctaje) {
        this.punctaje = Arrays.copyOf(punctaje, punctaje.length);
    }

    @Override
    public String toString() {
        String sb = "Curs{" + "profesor='" + profesor + '\'' +
                ", disciplina='" + disciplina + '\'' +
                ", dataSustinere=" + dataSustinere +
                ", anStudiu=" + anStudiu +
                ", facultate=" + facultate +
                ", punctaje=" + Arrays.toString(punctaje) +
                '}';
        return sb;
    }
}
