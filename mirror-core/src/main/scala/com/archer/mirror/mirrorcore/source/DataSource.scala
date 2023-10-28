package com.archer.mirror.mirrorcore.source

import com.archer.mirror.mirrorcore.common.MirrorContext
import org.apache.spark.sql.{Column, DataFrame, Dataset}

abstract class DataSource {

  def getDatasetName: String

  /**
   * Get different source dataset, such as file, hive, mysql
   * @return A spark dataset from different source
   */
  def getDataFrame: DataFrame

  /**
   * Get primary from dataset, used to make sure uniq key. And the primary maybe null
   * @return A collection contains some string
   */
  def getPrimary: Seq[String]

  /**
   * Check if the primary key in the dataset can confirm unique values.
   * @return true if this dataset's primary is legal
   */
  def checkPrimary(): Boolean = {
    val dataFrame = this.getDataFrame
    val primary = this.getPrimary
    if (dataFrame != null) {
      // use self implicit to group by Seq[String]
      import com.archer.mirror.mirrorcore.predef.GroupByFunction._
      val rows = dataFrame.groupBy(primary).count().orderBy("count").select("count").take(1)
      return rows.head.getAs[Long]("count") == 1
    }
    false
  }

  /**
   * Convert column names to other names
   * @param columnMap The old name and new name map
   * @param dataFrame Spark dataset from read file or load jdbc...
   * @return New spark dataset
   */
  def aliasColumnName(columnMap: Map[String, String], dataFrame: DataFrame): DataFrame = {
    var frame = dataFrame
    for (elem <- columnMap) {
      frame = frame.withColumnRenamed(elem._2, this.getDatasetName + "_" + elem._1)
    }
    frame
  }

}
