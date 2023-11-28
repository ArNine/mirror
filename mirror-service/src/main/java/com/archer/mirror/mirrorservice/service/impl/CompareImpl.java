package com.archer.mirror.mirrorservice.service.impl;

import com.archer.mirror.mirrorservice.common.MirrorSend;
import com.archer.mirror.mirrorservice.entity.DataCompareMeta;
import com.archer.mirror.mirrorservice.entity.FileSource;
import com.archer.mirror.mirrorservice.entity.SourceType;
import com.archer.mirror.mirrorservice.service.CompareService;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.*;
import java.util.stream.Collectors;

@Service
public class CompareImpl extends MirrorSend implements CompareService {


    private Set<String> readFileHeader(String filePath) throws IOException {
        FileInputStream file = new FileInputStream(filePath);
        XSSFWorkbook wb = new XSSFWorkbook(file);
        XSSFSheet ws = wb.getSheetAt(0);
        Row headerRow = ws.getRow(0);
        Set<String> headers = new HashSet<>();
        for (Cell cell : headerRow) {
            headers.add(cell.getStringCellValue());
        }
        return headers;
    }

    public void compare(String baseFilePath, String compareFilePath, List<String> primary) throws IOException {
        // read file and get header
        Set<String> baseHeader = readFileHeader(baseFilePath);
        Set<String> compareHeader = readFileHeader(compareFilePath);

        // check header
        if (!(baseHeader.size() == compareHeader.size() && baseHeader.containsAll(compareHeader))) {
            throw new RuntimeException("header not equals, please check file!");
        }

        // create json
        DataCompareMeta dataCompareMeta = new DataCompareMeta();
        FileSource baseFileSource = new FileSource(baseFilePath, new ArrayList<>(baseHeader));
        FileSource compareFileSource = new FileSource(compareFilePath, new ArrayList<>(compareHeader));
        dataCompareMeta.setBase(baseFileSource);
        dataCompareMeta.setCompare(compareFileSource);
        dataCompareMeta.setPrimary(primary);
        String queryID = sendToMirror(dataCompareMeta);
    }


}
