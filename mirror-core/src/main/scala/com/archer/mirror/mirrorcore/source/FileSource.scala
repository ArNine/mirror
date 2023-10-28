package com.archer.mirror.mirrorcore.source

import com.archer.mirror.mirrorcore.common.MirrorContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Column, DataFrame}

/**
 * This class is used to read files and convert them into datasets through Spark
 * @param fileType
 * @param filePath
 * @param columnMap
 */
class FileSource(fileType: String, filePath: String, datasetName: String, columnMap: Map[String, String]) extends DataSource {

  private val dataset = readFile(filePath)

  private var primary: Seq[String] = _
  private def readFile(filePath: String) = this.aliasColumnName(columnMap, MirrorContext.getOrCreateSpark().read
    .format(fileType).option("header", "true").load(filePath))


  def this(fileType: String, filePath: String, datasetName: String, columnMap: Map[String, String], primary: Seq[String]) = {
    this(fileType, filePath, datasetName, columnMap)
    this.primary = primary
    if (!this.checkPrimary()) {
      throw new Exception("the dataset's primary has same data, please checking dataset primary.")
    }
  }

  override def getDataFrame: DataFrame = {
    this.dataset
  }

  override def getPrimary: Seq[String] = {
    this.primary
  }

  override def getDatasetName: String = {
    this.datasetName
  }
}
