package org.example;

import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Exporter {





        final ExportConfig config;
        final List<Student> lista;

        public Exporter(ExportConfig config, List<Student> lista) {
            this.config = config;
            this.lista = new ArrayList(lista);
        }

        public void export(){
            System.out.println(config.getNumeFisier());
            for(Student s:lista){
                System.out.println(s);
            }
        }

        public void exportInAFile(){


            try {
                FileWriter writer = new FileWriter(config.getNumeFisier());
                for(Student s:lista){
                    writer.write(s.nrMatricol+","+s.prenume+","+s.nume+","+s.formatieDeStudiu+'\n');
                }
                writer.close(); // Important!
                System.out.println("Scrierea s-a realizat cu succes.");
            } catch (IOException e) {
                e.printStackTrace();
            }



        }
    }


