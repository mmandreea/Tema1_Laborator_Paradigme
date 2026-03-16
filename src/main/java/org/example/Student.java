package org.example;

import java.util.List;

public class Student {

    int nrMatricol;
    String prenume;
    String nume;
    String formatieDeStudiu;

    public Student(int nrM, String p, String n, String fS){
        this.nrMatricol=nrM;
        this.prenume=p;
        this.nume=n;
        this.formatieDeStudiu=fS;
    }

    public void afiseaza(){
        System.out.println(nrMatricol);
        System.out.println(prenume);
        System.out.println(nume);
        System.out.println(formatieDeStudiu);
    }
    @Override
    public String toString(){
      String msg=nrMatricol +" " +prenume+ " "+nume+" "+formatieDeStudiu;
      return msg;
    }

    public static boolean Prezenta(List<Student> studenti, Student studentCautat){
        for(Student student:studenti){
            if(student.equals(studentCautat))
                return true;
        }
        return false;
    }
     @Override
    public boolean equals(Object o){
        if(this.nume.equals(((Student)o).nume) && this.prenume.equals(((Student)o).prenume) && this.formatieDeStudiu.equals(((Student)o).formatieDeStudiu))
            return true;
        return false;
    }


}
