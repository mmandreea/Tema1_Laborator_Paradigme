package org.example;

import java.util.*;

import static java.util.Collections.sort;

public class Catalog {

    private static Catalog catalog;
   // private final List<Student> listaStudenti;
    //private final Map<Student, Integer> noteStudenti;

    private Catalog(){




    }

    public static Catalog getInstance(){
        if(catalog ==null)
            catalog=new Catalog();
        return catalog;
    }

    public static void sortareStudentiDupaFormatieDeStudiuSiNume(List<Student> lista) {
        sort(lista, new Comparator<Student>() {
            public int compare(Student s1, Student s2) {
                if (s1.formatieDeStudiu().equals(s2.formatieDeStudiu()))
                    return s1.nume().compareTo(s2.nume());
                return s1.formatieDeStudiu().compareTo(s2.formatieDeStudiu());
            }
        });
    }

    public static boolean prezenta(Set<Student> studenti, Student studentCautat) {
        return studenti.contains(studentCautat);
    }

    static Integer nota(Map<String, Integer> note, Student student) {
        return note.get(student.nrMatricol());
    }

    private static Map<Student, Integer> createMap(List<Student> studenti, Map<String, Integer> note) {

        Map<Student, Integer> noteStudenti = new HashMap<>();
        for (Student s : studenti) {
            noteStudenti.put(s, note.get(s.getNrMatricol()));
        }
        return noteStudenti;
    }

    static Integer notaStudent(Map<Student, Integer> noteStudenti, Student studentCautat) {
        return noteStudenti.entrySet().stream()
                .filter(pereche -> pereche.getKey().nrMatricol().equals(studentCautat.nrMatricol()))
                .map(Map.Entry::getValue)
                .findFirst()
                .orElse(null);
    }

    public Import getImporterFromFile(String... values){


        String fileExtension=values[0].substring(values[0].lastIndexOf('.')).toLowerCase();
        switch(fileExtension){
            case ".xlsx":
                return new ImportFromExcel(values[0]);
            case ".csv":
                return new ImportFromFile(values[0], values[1]);
            default:
                throw new IllegalArgumentException("Unknown file extension "+values[0]);
        }
    }

    public void importStudentiNote(List<Student> studenti, Map<Student, Integer> note, Import importer){
        // Golim colecțiile primite pentru siguranță (opțional)
        studenti.clear();
        note.clear();

        // Adăugăm TOATE elementele returnate de importer în lista originală
       // studenti.addAll(importer.importStudenti());

        // Adăugăm TOATE perechile returnate de importer în map-ul original
        note.putAll(importer.importNote(studenti));
    }

    public List<Student> importStudenti(Import importer){
        List<Student> studenti= new ArrayList<>();
        studenti.addAll(importer.importStudenti());
        return studenti;
    }

}
