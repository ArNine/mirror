package com.archer.mirror.mirrorcore.source

import com.archer.mirror.mirrorcore.common.MirrorContext
import org.apache.spark.SparkConf
import org.apache.spark.sql.{Column, DataFrame}

class FileSource(filePath: String) extends DataSource {

  private val dataset = readFile(filePath)
  private var primary: Seq[Column] = _
  private def readFile(filePath: String) = MirrorContext.getOrCreateSpark().read
    .format("json").option("header", "true").load(filePath)


  def this(filePath: String, primary: Seq[String]) = {
    this(filePath)
    val spark = MirrorContext.getOrCreateSpark()
    import spark.implicits._
    this.primary = primary.map(x => {
      $"$x"
    })
    if (!this.checkPrimary()) {
      throw new Exception("the dataset's primary has same data, please checking dataset primary.")
    }
  }

  override def getDataFrame: DataFrame = {
    this.dataset
  }

  override def getPrimary: Seq[Column] = {
    this.primary
  }
}
