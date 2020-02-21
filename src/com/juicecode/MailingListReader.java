package com.juicecode;

import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.*;

import javax.swing.text.JTextComponent;
import java.io.*;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

public class MailingListReader {

    private JTextComponent filePath;

    public MailingListReader(JTextComponent filePath) {
        this.filePath = filePath;
    }

    public Map<String, MailContent> getMailingList() throws IOException {
        File mailingListExcel = new File(filePath.getText());
        FileInputStream fis = new FileInputStream(mailingListExcel);
        XSSFWorkbook workbook = new XSSFWorkbook(fis);
        XSSFSheet sheet = workbook.getSheetAt(0);
        Iterator<Row> rowIterator = sheet.iterator();

        Map<String, MailContent> mailingList = new HashMap<String, MailContent>();
        while (rowIterator.hasNext()) {
            Row row = rowIterator.next();

            String to = row.getCell(1).getStringCellValue();
            String subject = row.getCell(2).getStringCellValue();
            String content = row.getCell(3).getStringCellValue();

            mailingList.put(to, new MailContent(subject, content));
        }

        return mailingList;
    }
}
