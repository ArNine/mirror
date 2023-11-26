package com.archer.mirror.mirrorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class FileSource extends SourceType{

    private String sourceFilePath;

    private List<String> columns;

    @Override
    public String getSourceType() {
        return SourceType.FILE_SOURCE;
    }
}
