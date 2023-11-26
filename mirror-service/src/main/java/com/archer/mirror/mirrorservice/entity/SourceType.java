package com.archer.mirror.mirrorservice.entity;

public abstract class SourceType {

    public static String FILE_SOURCE = "File";
    public static String HIVE_SOURCE = "Hive";

    abstract public String getSourceType();

}
