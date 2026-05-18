package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.List;

public class ExportToFile implements Exporter{

    final private String fileName;

    public ExportToFile(String fileName) {
        this.fileName = fileName;
    }

    public void export(List<Student> lista) {


        try {
            FileWriter writer = new FileWriter(fileName);
            for(Student s:lista){
                writer.write(s.nrMatricol()+","+s.prenume()+","+s.nume()+","+s.formatieDeStudiu()+'\n');
            }
            writer.close(); // Important!
            System.out.println("Scrierea s-a realizat cu succes.");
        } catch (IOException e) {
            e.printStackTrace();
        }



    }

}
