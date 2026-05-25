package org.example;

import java.io.File;
import java.util.*;
import java.util.function.Function;
import java.util.stream.Collectors;

import static java.util.Collections.sort;


public class Main {



    static void afisareLista(List<Student> studenti) {
        for (Student s : studenti) {
            s.afiseaza();
        }
    }



    public static List<Student> citire(String numeFisier) {
        List<Student> lista = new ArrayList<>();
        Scanner fin = null;
        try {
            File fisier = new File(numeFisier);
            fin = new Scanner(fisier);
            while (fin.hasNext()) {
                String[] vecStudent = fin.nextLine().split(",");
                String nrM = vecStudent[0];
                String p = vecStudent[1];
                String n = vecStudent[2];
                String fS = vecStudent[3];
                lista.add(new Student(nrM, p, n, fS));
            }
            return lista;

        } catch (Exception e) {
            System.err.println("A apărut o eroare la citire: " + e.getMessage());
            throw new RuntimeException("Fisierul nu a fost gasit!");
        } finally {
            fin.close();
        }


    }

    public static Map<String, Integer> citireNote(Map<String, Integer> note, String numeFisier) {

        Scanner fin = null;
        try {
            File fisier = new File(numeFisier);
            fin = new Scanner(fisier);
            while (fin.hasNext()) {
                String noteVec[] = fin.nextLine().split(",");
                String nrMatricol = noteVec[0];
                Integer note1 = Integer.valueOf(noteVec[1]);
                note.put(nrMatricol, note1);
            }
            return note;
        } catch (Exception e) {

            System.err.println("A apărut o eroare la citire: " + e.getMessage());
            throw new RuntimeException("Fisierul nu a fost gasit!");
        } finally {
            fin.close();
        }

    }



    static void printNotaStudent(Student studentCautat, Map<Student, Integer> noteStudenti) {

        Integer nota = noteStudenti.get(studentCautat);
        if (nota == null)
            System.out.println("Studentul nu are o nota inregistrata!");
        else
            System.out.println("Studentul are nota " + nota);
    }


    static void main() {

        Catalog catalog=Catalog.getInstance();
        /*
        Student student1=new Student(3568, "Andreea", "Mata", "C221");
        Student student2 = new Student(3569, "Matei", "Ionescu", "C221");
        Student student3 = new Student(3570, "Elena", "Popescu", "B114");
        Student student4 = new Student(3571, "Adrian", "Dumitru", "A312");
        Student student5 = new Student(3572, "Ioana", "Stancu", "C221");
        Student student6 = new Student(3573, "Cristian", "Marin", "B114");
        Student studentDeCautat = new Student(3574, "Sofia", "Dragomir", "A312");
        */

        List<Student> studenti = new ArrayList<>();
        /*
        studenti.add(student1);
        studenti.add(student2);
        studenti.add(student3);
        studenti.add(student4);
        studenti.add(student5);
        studenti.add(student6);
        studenti.add(studentDeCautat);
         */

        Map<Student, Integer> noteStudent = new HashMap<>();
        catalog.importStudentiNote(studenti, noteStudent, catalog.getImporterFromFile("StudentiFisier.csv", "Note.csv"));
        Student student8 = new Student(null, "Sofia", "Dragomir", "A312");

        Set<Student> set = new HashSet<>(studenti); ///se copiaza lista in HashSet

        if (catalog.prezenta(set, student8))
            System.out.println("Studentul se afla in sala de curs.");
        else
            System.out.println("Studentul NU se afla in sala de curs.");


        catalog.sortareStudentiDupaFormatieDeStudiuSiNume(studenti);
        afisareLista(studenti);
        Map<String, Integer> note = new HashMap<>();
        citireNote(note, "Note.csv");
        Student studentDeCautat = new Student("3574", "Sofia", "Dragomir", "A312");
        System.out.println("Studentul cu numarul matricol 3574 are nota: " + catalog.nota(note, studentDeCautat));

        /*
         creeaza CreateMap astfel incat cautarea sa mearga fara a stii numarul matricol, avand doar obiectul de Student
        */
        //Map<Student, Integer> noteStudent = new HashMap<>();
        //noteStudent = createMap(studenti, note);
        //Student student1 = new Student("3568", "Andreea", "Mata", "C221");
        //printNotaStudent(student1, noteStudent);

       exportList(studenti, getExporterToFile("fisier.xlsx"));

        noteStudent.entrySet().stream()
                .filter(pereche -> pereche.getValue() == 10)
                .forEach(pereche -> pereche.getKey().afiseaza());

        System.out.println("-------------------------------------------------------------------------------------------------");

        noteStudent.entrySet().stream()
                .filter(pereche -> pereche.getValue() <=4)
                .forEach(pereche -> pereche.getKey().afiseaza());

        System.out.println("-------------------------------------------------------------------------------------------------");

        Optional<Integer> sumaNotelor = noteStudent.values().stream()
                // Acum prin stream curg doar numere (ex: 8, 10, 9, 10)
                .reduce((acc, nota) -> acc + nota);

        int numarNote=noteStudent.size();
        if(sumaNotelor.isPresent())
            System.out.println("Media Notelor este: "+ sumaNotelor.get()/(numarNote*1.0));
        else
            System.out.println("Nu exista studenti.");

        System.out.println("-------------------------------------------------------------------------------------------------");

        Map<Integer, Integer> frecventaNote = noteStudent.values().stream()
                .collect(Collectors.toMap(
                        nota -> nota,
                        numarAparitii -> 1,
                        Integer::sum
                ));
        System.out.println("Frecvența notelor este: ");
        frecventaNote.forEach((nota, numarAparitii) ->
                System.out.println("Nota " + nota + " apare de " + numarAparitii + " ori.")
        );

        System.out.println("-------------------------------------------------------------------------------------------------");

        List<Student> listaSemigrupa1=studenti.stream()
                .sorted()
                .limit(studenti.size()/2)
                .map(s -> new Student(s.nrMatricol(), s.prenume(), s.nume(), "Semigrupa 1"))
                .collect(Collectors.toList());


        List<Student> listaSemigrupa2=studenti.stream()
                .sorted()
                .skip(studenti.size()/2)
                .map(s -> new Student(s.nrMatricol(), s.prenume(), s.nume(), "Semigrupa 2"))
                .collect(Collectors.toList());
        System.out.println("Semigrupa1: ");
        for(Student s:listaSemigrupa1){
            s.afiseaza();
        }
        System.out.println("Semigrupa2: ");
        for(Student s:listaSemigrupa2){
            s.afiseaza();
        }

        System.out.println("-------------------------------------------------------------------------------------------------");

        Map<Integer, Integer> frecventaNoteSemigrupa1 = listaSemigrupa1.stream()
                .map(student -> catalog.notaStudent(noteStudent, student))
                .collect(Collectors.toMap(
                        nota -> nota,
                        numarAparitii -> 1,
                        Integer::sum
                ));
        System.out.println("Frecventa notelor in semigrupa 1 este: ");
        frecventaNoteSemigrupa1.forEach((nota, numarAparitii) ->
                System.out.println("Nota " + nota + " apare de " + numarAparitii + " ori.")
        );

        System.out.println("-------------------------------------------------------------------------------------------------");

        Map<Integer, Integer> frecventaNoteSemigrupa2 = listaSemigrupa2.stream()
                .map(student -> catalog.notaStudent(noteStudent, student))
                .collect(Collectors.toMap(
                        nota -> nota,
                        numarAparitii -> 1,
                        Integer::sum
                ));
        System.out.println("Frecventa notelor in semigrupa 2 este: ");
        frecventaNoteSemigrupa2.forEach((nota, numarAparitii) ->
                System.out.println("Nota " + nota + " apare de " + numarAparitii + " ori.")
        );

    }
    private static void exportList(List<Student> list, Exporter exporter){
        exporter.export(list);
    }
    private static Exporter getExporterToFile(String fileName){

        System.out.println("Andreea");
        String fileExtension=fileName.substring(fileName.lastIndexOf('.'));
        switch(fileExtension){
            case ".xlsx":
                return new ExportToExcel(fileName);
            case ".csv":
                return new ExportToFile(fileName);
            default:
                throw new IllegalArgumentException("Unknown file extension "+fileName);
        }


    }




}
