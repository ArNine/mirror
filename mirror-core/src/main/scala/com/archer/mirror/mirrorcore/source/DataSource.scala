package com.archer.mirror.mirrorcore.source

import com.archer.mirror.mirrorcore.common.MirrorContext
import org.apache.spark.sql.{Column, DataFrame, Dataset}

abstract class DataSource {


  def getDataFrame: DataFrame

  def getPrimary: Seq[Column]

  def checkPrimary(): Boolean = {
    val dataFrame = this.getDataFrame
    val primary = this.getPrimary
    if (dataFrame != null) {
      val rows = dataFrame.groupBy(primary: _*).count().orderBy("count").select("count").take(1)
      return rows.head.getAs[Long]("count") == 1
    }
    false
  }

}
