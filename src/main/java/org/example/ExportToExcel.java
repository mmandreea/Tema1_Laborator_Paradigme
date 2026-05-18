package org.example;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

public class ExportToExcel implements Exporter{

    final private String fileName;

    public ExportToExcel(String fileName) {
        this.fileName = fileName;
    }
    public void export(List<Student> lista){
        XSSFWorkbook workbook = new XSSFWorkbook();
        XSSFSheet sheet = workbook.createSheet(fileName);
        Map<String, Object[]> data = new TreeMap<>();
        String i="1";
        for(Student s:lista){
            data.put(i, new Object[]{s.getNrMatricol(), s.getNume(), s.getPrenume(), s.getFormatieDeStudiu()});
            i=i+1;
        }

        int rowNum=0;

        for (String key : data.keySet()) {
            Row row = sheet.createRow(rowNum++);
            Object[] objArr = data.get(key);
            int cellNum = 0;
            for (Object obj : objArr) {
                Cell cell = row.createCell(cellNum++);
                if (obj instanceof String)
                    cell.setCellValue((String) obj);
                else if (obj instanceof Integer)
                    cell.setCellValue((Integer) obj);
            }
        }

        try (FileOutputStream out = new FileOutputStream(fileName)) {
            workbook.write(out);
            System.out.println(fileName+" written successfully.");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
