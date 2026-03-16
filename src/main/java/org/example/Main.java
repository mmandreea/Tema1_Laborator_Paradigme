package org.example;

import java.io.File;
import java.util.*;

import static java.util.Collections.sort;


public class Main {

    static void sortareStudentiDupaFormatieDeStudiuSiNume(List<Student> lista){
        sort(lista, new Comparator<Student>(){
            public int compare(Student s1, Student s2) {
                if (s1.formatieDeStudiu.equals(s2.formatieDeStudiu))
                    return s1.nume.compareTo(s2.nume);
                return s1.formatieDeStudiu.compareTo(s2.formatieDeStudiu);
            }
        });
    }

    static void afisareLista(List<Student> studenti)
    {
        for(Student s:studenti)
        {
            s.afiseaza();
        }
    }

    public static boolean prezenta(Set<Student> studenti, Student studentCautat){
        return studenti.contains(studentCautat);
    }

    public static List<Student> citire(String numeFisier){
        List<Student> lista=new ArrayList<>();
        Scanner fin=null;
        try{
            File fisier=new File(numeFisier);
            fin=new Scanner(fisier);
            while(fin.hasNext()){
                String[] vecStudent=fin.nextLine().split(",");
                String nrM=vecStudent[0];
                String p=vecStudent[1];
                String n=vecStudent[2];
                String fS=vecStudent[3];
                lista.add(new Student(nrM, p, n, fS));
            }
            return lista;

        }
        catch(Exception e){
            System.err.println("A apărut o eroare la citire: " + e.getMessage());
            throw new RuntimeException("Fisierul nu a fost gasit!");
        }
        finally{
            fin.close();
        }



    }
    static Map<String,Integer> citireNote( Map<String, Integer> note, String numeFisier)
    {

        Scanner fin=null;
        try{
            File fisier=new File(numeFisier);
            fin=new Scanner(fisier);
            while(fin.hasNext()){
                String noteVec[]=fin.nextLine().split(",");
                String nrMatricol=noteVec[0];
                Integer note1=Integer.valueOf(noteVec[1]);
                note.put(nrMatricol, note1);
            }
            return note;
        }
        catch(Exception e){

            System.err.println("A apărut o eroare la citire: " + e.getMessage());
            throw new RuntimeException("Fisierul nu a fost gasit!");
        }
        finally{
            fin.close();
        }

    }

    static Integer nota(Map<String, Integer> note, Student student){
        return note.get(student.nrMatricol);
    }

    static void main() {
        /*
        Student student1=new Student(3568, "Andreea", "Mata", "C221");
        Student student2 = new Student(3569, "Matei", "Ionescu", "C221");
        Student student3 = new Student(3570, "Elena", "Popescu", "B114");
        Student student4 = new Student(3571, "Adrian", "Dumitru", "A312");
        Student student5 = new Student(3572, "Ioana", "Stancu", "C221");
        Student student6 = new Student(3573, "Cristian", "Marin", "B114");
        Student studentDeCautat = new Student(3574, "Sofia", "Dragomir", "A312");
        */

        List<Student> studenti=new ArrayList<>();
        /*
        studenti.add(student1);
        studenti.add(student2);
        studenti.add(student3);
        studenti.add(student4);
        studenti.add(student5);
        studenti.add(student6);
        studenti.add(studentDeCautat);
         */
        studenti= citire("StudentiFisier.csv");
        Student student8 = new Student(null, "Sofia", "Dragomir", "A312");

        Set<Student> set=new HashSet<>(studenti); ///se copiaza lista in HashSet

        if(prezenta(set, student8))
            System.out.println("Studentul se afla in sala de curs.");
        else
            System.out.println("Studentul NU se afla in sala de curs.");

        sortareStudentiDupaFormatieDeStudiuSiNume(studenti);
        afisareLista(studenti);
        Map<String, Integer> note=new HashMap<>();
        citireNote(note, "Note.csv");
        Student studentDeCautat = new Student("3574", "Sofia", "Dragomir", "A312");
        System.out.println("Studentul cu numarul matricol 3574 are nota: "+nota(note, studentDeCautat));
    }

}
