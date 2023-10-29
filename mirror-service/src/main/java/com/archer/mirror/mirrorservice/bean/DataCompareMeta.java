package com.archer.mirror.mirrorservice.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;
import java.util.Map;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataCompareMeta {
    private Map<String, String> base;
    private Map<String, String> compare;
    private List<String> primary;
}
