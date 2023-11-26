package com.archer.mirror.mirrorservice.service.impl;


import com.archer.mirror.mirrorservice.service.FileProcessService;
import org.apache.commons.codec.digest.DigestUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.CellType;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFRow;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.util.*;
import java.util.concurrent.Future;

import static org.apache.poi.ss.usermodel.CellType.NUMERIC;


@Service
public class FileProcessImpl implements FileProcessService {


    private static final int NUM_ROWS_TO_READ = 20;

    @Override
    public String uploadFile(MultipartFile file) throws Exception{
        InputStream inputStream = file.getInputStream();
        String fileName = DigestUtils.md5Hex(inputStream) + ".xlsx";
        FileOutputStream outputStream = new FileOutputStream(fileName);
        byte[] buffer = new byte[1024];
        int bytesRead;
        while ((bytesRead = inputStream.read(buffer)) != -1) {
            outputStream.write(buffer, 0, bytesRead);
        }
        return fileName;
    }

    public Map<String, Object> readFile(String filePath) throws IOException {
        Map<String, Object> result = new HashMap<>();
        try {
            FileInputStream file = new FileInputStream(filePath);
            //Create Workbook instance holding reference to .xlsx file
            XSSFWorkbook wb = new XSSFWorkbook(file);
            //Get first/desired sheet from the workbook
            XSSFSheet ws = wb.getSheetAt(0);

            Row headerRow = ws.getRow(0);
            List<String> headers = new LinkedList<>();
            for (Cell cell : headerRow) {
                headers.add(cell.getStringCellValue());
            }
            result.put("headers", headers);
            System.out.println(headers);
            //Iterate through each rows one by one
            List<Map<String, Object>> data = new LinkedList<>();
            int rowCount = Math.min(ws.getPhysicalNumberOfRows(), NUM_ROWS_TO_READ);
            for (int i = 1;i < rowCount;i ++) {
                Row row = ws.getRow(i);
                //For each row, iterate through all the columns
                Map<String, Object> rowData = new HashMap<>();
                for (int j = 0; j < headers.size(); j++) {
                    Cell cell = row.getCell(j);
                    if (cell != null) {
                        switch (cell.getCellType()) {
                            case NUMERIC:
                                rowData.put(headers.get(j), cell.getNumericCellValue());
                                break;
                            case STRING:
                                rowData.put(headers.get(j), cell.getStringCellValue());
                                break;
                        }
                    }
                }
                data.add(rowData);
            }
            result.put("data", data);
            file.close();
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return result;
    }


    @Async
    @Override
    public Future<String> sendToMirror() throws InterruptedException {
        Thread.sleep(5000);
        return new AsyncResult<>("task Done");
    }




}
