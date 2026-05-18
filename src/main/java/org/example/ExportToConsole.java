package org.example;

import java.util.List;

public class ExportToConsole implements Exporter{

    public void export(List<Student> lista){
        for(Student s:lista){
            System.out.println(s);
        }
    }
}
