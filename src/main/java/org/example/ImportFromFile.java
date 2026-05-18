package org.example;
import java.io.File;
import java.util.*;

public class ImportFromFile implements Import{

    final private String fileNameStudenti;
    final private String fileNameNote;

    public ImportFromFile(String fileNameStudenti, String fileNameNote) {
        this.fileNameStudenti = fileNameStudenti;
        this.fileNameNote = fileNameNote;
    }

    @Override
    public List<Student> importStudenti() {

        List<Student> lista = new ArrayList<>();
        Scanner fin = null;
        try {
            File fisier = new File(fileNameStudenti);
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

    @Override
    public Map<Student, Integer> importNote(List<Student> studenti) {
        Map<Student, Integer> note = new HashMap<>();
        Scanner fin = null;
        int i=0;
        try {
            File fisier = new File(fileNameNote);
            fin = new Scanner(fisier);
            while (fin.hasNext()) {
                String noteVec[] = fin.nextLine().split(",");
                Integer note1 = Integer.valueOf(noteVec[1]);
                note.put(studenti.get(i), note1);
                i++;
            }
            return note;
        } catch (Exception e) {

            System.err.println("A apărut o eroare la citire: " + e.getMessage());
            throw new RuntimeException("Fisierul nu a fost gasit!");
        } finally {
            fin.close();
        }


    }
}
