package org.example;

import java.util.List;
import java.util.Map;

public interface Import {

    public List<Student> importStudenti();
    public Map<Student, Integer> importNote(List<Student> studenti);

}
