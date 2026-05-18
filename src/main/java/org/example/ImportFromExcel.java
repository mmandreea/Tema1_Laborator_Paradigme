package org.example;

import java.io.File;
import java.util.*;
import java.io.FileInputStream;
import java.io.IOException;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ImportFromExcel implements Import{

    final private String fileName;

    public ImportFromExcel( String fileName) {
        this.fileName=fileName;
    }

    @Override
    public List<Student> importStudenti() {

        List<Student> list=new ArrayList();
        try (FileInputStream file = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheetAt(0);

            Iterator<Row> rowIterator = sheet.iterator();
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell = cellIterator.next();
                String numarMatricol=cell.getStringCellValue();
                String nume=cell.getStringCellValue();
                String prenume=cell.getStringCellValue();
                String formatiuneDeStudiu=cell.getStringCellValue();
                list.add(new Student(numarMatricol, prenume, nume, formatiuneDeStudiu));

            }

        } catch (IOException e) {
            e.printStackTrace();
        }
        return list;

    }

    @Override
    public Map<Student, Integer> importNote(List<Student> studenti) {
        Map<Student, Integer> note = new HashMap<>();
        try (FileInputStream file = new FileInputStream(fileName);
             XSSFWorkbook workbook = new XSSFWorkbook(file)) {

            XSSFSheet sheet = workbook.getSheetAt(1);

            Iterator<Row> rowIterator = sheet.iterator();
            int i=0;
            while (rowIterator.hasNext()) {
                Row row = rowIterator.next();
                Iterator<Cell> cellIterator = row.cellIterator();
                Cell cell = cellIterator.next();
                int notaStudent=(int)cell.getNumericCellValue();
                note.put(studenti.get(i), notaStudent);
                i++;
            }

        } catch (IOException e) {
            e.printStackTrace();
        }

        return note;
    }
}
