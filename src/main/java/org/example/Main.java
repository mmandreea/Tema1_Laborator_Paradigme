package org.example;

import java.util.ArrayList;
import java.util.List;

/// 1. Liste cu studenti
/// 2. O functie care returneaza true daca un student este prezent(se afla in lista)

public class Main {
    static void main() {
        Student student1=new Student(3568, "Andreea", "Mata", "C221");
        Student student2 = new Student(3569, "Matei", "Ionescu", "C221");
        Student student3 = new Student(3570, "Elena", "Popescu", "B114");
        Student student4 = new Student(3571, "Adrian", "Dumitru", "A312");
        Student student5 = new Student(3572, "Ioana", "Stancu", "C221");
        Student student6 = new Student(3573, "Cristian", "Marin", "B114");
        Student student7 = new Student(3574, "Sofia", "Dragomir", "A312");
        List<Student> studenti=new ArrayList();
        studenti.add(student1);
        studenti.add(student2);
        studenti.add(student3);
        studenti.add(student4);
        studenti.add(student5);
        studenti.add(student6);
        ///studenti.add(student7);

        if(Student.Prezenta(studenti, student7))
            System.out.println("Studentul se afla in sala de curs.");
        else
            System.out.println("Studentul NU se afla in sala de curs.");
    }

}
