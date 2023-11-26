package com.archer.mirror.mirrorservice.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCompareMeta {
    private SourceType base;
    private SourceType compare;
    private List<String> primary;
}
