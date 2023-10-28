package com.archer.mirror.mirrorcore.predef


import com.archer.mirror.mirrorcore.common.MirrorContext
import org.apache.spark.sql.{Column, DataFrame, RelationalGroupedDataset}

import scala.language.implicitConversions

class AddGroupByFunction(dataFrame: DataFrame) {
  def groupBy(primary: Seq[String]): RelationalGroupedDataset = {
    val spark = MirrorContext.getOrCreateSpark()
    import spark.implicits._
    val columns: Seq[Column] = primary.map(x => {
      $"$x"
    })
    dataFrame.groupBy(columns: _*)
  }
}
object GroupByFunction{
  implicit def addFunction(dataFrame : DataFrame): AddGroupByFunction = new AddGroupByFunction(dataFrame)
}
