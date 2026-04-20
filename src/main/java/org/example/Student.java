package org.example;

import java.io.File;
import java.util.*;

import static java.util.Collections.sort;

public class Student{

    final String nrMatricol;
    final String prenume;
    final String nume;
    final String formatieDeStudiu;

    public Student(String nrM, String p, String n, String fS){
        this.nrMatricol=nrM;
        this.prenume=p;
        this.nume=n;
        this.formatieDeStudiu=fS;
    }

    public void afiseaza(){
        System.out.print(nrMatricol+" ");
        System.out.print(prenume+" ");
        System.out.print(nume+" ");
        System.out.println(formatieDeStudiu);
    }
    @Override
    public String toString(){
      String msg=nrMatricol +" " +prenume+ " "+nume+" "+formatieDeStudiu;
      return msg;
    }

     @Override
    public boolean equals(Object o){
        if(this.nume.equals(((Student)o).nume) && this.prenume.equals(((Student)o).prenume) && this.formatieDeStudiu.equals(((Student)o).formatieDeStudiu))
            return true;
        return false;
    }

    @Override
    public int hashCode() {
        return Objects.hash(prenume, nume, formatieDeStudiu);
    }

    public String getNrMatricol() {
        return nrMatricol;
    }

    public String getFormatieDeStudiu() {
        return formatieDeStudiu;
    }

    public String getNume() {
        return nume;
    }
    /*
    @Override
    public int compareTo(Student o) {
        int cmp=this.nume.compareTo(o.nume);
        if(cmp!=0)
            return cmp;
        return Integer.compare(this.nrMatricol,o.nrMatricol);
    }
    */


}
