package studentTest;

import org.example.Catalog;
import org.example.Student;
import org.junit.jupiter.api.Test;

import java.util.*;

import static org.example.Main.*;
import static org.junit.jupiter.api.Assertions.*;

class MainTest {

    Catalog catalog = Catalog.getInstance();
    @Test
    void sortareStudentiDupaFormatieDeStudiuSiNumeTotiAuAceeasiFormatiune() {

        Student student1 = new Student("3568", "Andreea", "Mata", "C221");
        List<Student> studenti = new ArrayList<>();
        studenti = citire("StudentiFisierAceeasiFormatiune.csv");
        catalog.sortareStudentiDupaFormatieDeStudiuSiNume(studenti);
        for (int i = 0; i < studenti.size() - 1; i++) {
            Student s1 = studenti.get(i);
            Student s2 = studenti.get(i + 1);
            if (s1.getFormatieDeStudiu().equals(s2.getFormatieDeStudiu())) {
                assertTrue(s1.getNume().compareTo(s2.getNume()) <= 0);
            } else {
                assertTrue(s1.getFormatieDeStudiu().compareTo(s2.getFormatieDeStudiu()) <= 0);
            }
        }
    }
    @Test
    void sortareStudentiDupaFormatieDeStudiuSiNumeTotiAuFormatiiDiferite() {

        Student student1 = new Student("3568", "Andreea", "Mata", "C221");
        List<Student> studenti = new ArrayList<>();
        studenti = citire("StudentiFisier.csv");
        catalog.sortareStudentiDupaFormatieDeStudiuSiNume(studenti);
        for (int i = 0; i < studenti.size() - 1; i++) {
            Student s1 = studenti.get(i);
            Student s2 = studenti.get(i + 1);
            if (s1.getFormatieDeStudiu().equals(s2.getFormatieDeStudiu())) {
                assertTrue(s1.getNume().compareTo(s2.getNume()) <= 0);
            } else {
                assertTrue(s1.getFormatieDeStudiu().compareTo(s2.getFormatieDeStudiu()) <= 0);
            }
        }
    }

    @Test
    void afisareLista() {
    }

    @Test
    void prezentaStudentulEstePrezent() {
        Student student1 = new Student("3568", "Andreea", "Mata", "C221");
        List<Student> studenti = new ArrayList<>();
        studenti = citire("StudentiFisier.csv");
        Set<Student> set = new HashSet<>(studenti); ///se copiaza lista in HashSet
        assertEquals(true, catalog.prezenta(set, student1), "Studentul este prezent!");
    }

    @Test
    void prezentaStudentulEstePrezentFaraNumarMatricol() {
        Student student1 = new Student(null, "Andreea", "Mata", "C221");
        List<Student> studenti = new ArrayList<>();
        studenti = citire("StudentiFisier.csv");
        Set<Student> set = new HashSet<>(studenti); ///se copiaza lista in HashSet
        assertEquals(true, catalog.prezenta(set, student1), "Studentul este prezent!");
    }

    @Test
    void prezentaStudentulNuEstePrezent() {
        Student student1 = new Student("3568", "Mihaela", "Anca", "C221");
        List<Student> studenti = new ArrayList<>();
        studenti = citire("StudentiFisier.csv");
        Set<Student> set = new HashSet<>(studenti); ///se copiaza lista in HashSet
        assertEquals(false, catalog.prezenta(set, student1), "Studentul nu este prezent!");
    }

    @Test
    void notaStudent() {

    }
}