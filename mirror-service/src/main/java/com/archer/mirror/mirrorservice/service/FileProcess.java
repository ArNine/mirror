package com.archer.mirror.mirrorservice.service;


import com.archer.mirror.mirrorservice.bean.DataCompareMeta;
import com.archer.mirror.mirrorservice.utils.FileUtils;
import com.univocity.parsers.csv.CsvParser;
import com.univocity.parsers.csv.CsvParserSettings;
import org.springframework.scheduling.annotation.Async;
import org.springframework.scheduling.annotation.AsyncResult;
import org.springframework.stereotype.Service;
import org.springframework.ui.Model;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Reader;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.*;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;


@Service
public class FileProcess implements FileProcessService{

    @Override
    public DataCompareMeta uploadFile(MultipartFile[] files, Model model) {
        try {
            ArrayList<Map<String, String>> colData = new ArrayList<>();
            // 1. upload file
            for (MultipartFile file : files) {
                HashMap<String, String> col = new HashMap<>();
                if (file.getSize() <= 0) {
                    continue;
                }
                final String newFileName = FileUtils.save(file, "");
                // 2. create json prop and send to mirror-core
                // TODO it should be only read 1 rows
                try (Reader inputReader = new InputStreamReader(Files.newInputStream(new File(newFileName + ".csv").toPath()), StandardCharsets.UTF_8)) {
                    CsvParser parser = new CsvParser(new CsvParserSettings());
                    List<String[]> parsedRows = parser.parseAll(inputReader);
                    String[] colsName = parsedRows.get(0);
                    for (int i = 0;i < colsName.length;i ++) {
                        col.put("c" + i, colsName[i]);
                    }

                } catch (IOException e) {
                    // handle exception
                }
                colData.add(col);
            }
            DataCompareMeta dataCompareMeta = new DataCompareMeta();
            dataCompareMeta.setBase(colData.get(0));
            dataCompareMeta.setBase(colData.get(1));
            dataCompareMeta.setPrimary(Collections.singletonList("c10"));
            // 3. asynchronous  for result
            Future<String> future = this.sendToMirror();
            return dataCompareMeta;
        } catch (IOException | InterruptedException e) {
            throw new RuntimeException(e);
        }
    }


    @Async
    @Override
    public Future<String> sendToMirror() throws InterruptedException {
        Thread.sleep(5000);
        return new AsyncResult<>("task Done");
    }




}
