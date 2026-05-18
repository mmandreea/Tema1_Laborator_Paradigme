package org.example;

import java.util.Objects;

public record Student(
        String nrMatricol,
        String prenume,
        String nume,
        String formatieDeStudiu
) implements Comparable <Student>{

    @Override
    public int compareTo(Student celalaltStudent) {
        // Pasul 1: Comparăm studenții după numele de familie
        int rezultatNume = this.nume.compareTo(celalaltStudent.nume);

        // Dacă numele sunt diferite, returnăm rezultatul (am stabilit ordinea)
        if (rezultatNume != 0) {
            return rezultatNume;
        }

        // Pasul 2: Dacă numele de familie sunt identice (ex: doi Popescu),
        // facem departajarea după numărul matricol.
        // Deoarece nrMatricol este String, folosim tot compareTo()
        return this.nrMatricol.compareTo(celalaltStudent.nrMatricol);
    }
    // 1. Metodele tale custom rămân neschimbate
    public void afiseaza() {
        System.out.print(nrMatricol + " ");
        System.out.print(prenume + " ");
        System.out.print(nume + " ");
        System.out.println(formatieDeStudiu);
    }

    // 2. Suprascriem toString pentru a păstra formatul tău exact
    @Override
    public String toString() {
        return nrMatricol + " " + prenume + " " + nume + " " + formatieDeStudiu;
    }

    // 3. Suprascriem equals pentru că ai o logică specifică (fără nrMatricol)
    @Override
    public boolean equals(Object o) {
        // O mică îmbunătățire standardizată pentru equals
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Student student = (Student) o;
        return nume.equals(student.nume) &&
                prenume.equals(student.prenume) &&
                formatieDeStudiu.equals(student.formatieDeStudiu);
    }

    // 4. Suprascriem hashCode pentru a fi sincronizat cu equals-ul de mai sus
    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu);
    }

    // ==========================================
    // NOTĂ DESPRE GETTERE ÎN RECORDURI
    // ==========================================
    // Java generează automat metode de acces care se numesc EXACT ca variabilele.
    // Ex: în loc de student.getNume(), la recorduri folosești student.nume()
    //
    // Am lăsat getterele de mai jos ca să NU îți crape codul pe care îl ai
    // deja scris în clasa Main (unde apelai .getNume(), .getNrMatricol() etc.).
    // Dacă modifici în Main să folosească .nume() în loc de .getNume(),
    // poți șterge complet aceste 4 metode de mai jos!

    public String getNrMatricol() {
        return nrMatricol; // sau return this.nrMatricol()
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    public String getNume() {
        return nume;
    }

    public String getPrenume() {
        return prenume;
    }


}