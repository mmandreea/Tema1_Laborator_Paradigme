package org.example;

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


}
