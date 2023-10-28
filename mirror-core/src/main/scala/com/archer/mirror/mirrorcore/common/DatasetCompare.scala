package com.archer.mirror.mirrorcore.common

import com.archer.mirror.mirrorcore.bean.{Diff, StringColumResult}
import com.archer.mirror.mirrorcore.source.DataSource

import scala.collection.mutable.ListBuffer

abstract class Compare {

  def compare: ListBuffer[Diff]

}


class DatasetCompare (base: DataSource, target: DataSource) extends Compare {

  def compare: ListBuffer[Diff] = {
    // TODO 得到数据集元数据
    val baseCount = base.getDataFrame.count
    val targetCount = target.getDataFrame.count
    var result = ListBuffer[Diff]()
    // TODO 判断有无主键
    if (base.getPrimary != null && target.getPrimary != null) {
      // 使用主键进行聚合
      val joinDataset = base.getDataFrame.join(target.getDataFrame, base.getPrimary)
      val stringResultColumn = new StringColumResult("Primary", base.getDataFrame.count, "Primary", target.getDataFrame.count, joinDataset.count)
      result.append(stringResultColumn)
      // 判断各列的diff
      base.getDataFrame.columns
      for ((baseCol, compareCol) <- this.base.getDataFrame.columns.zip(this.target.getDataFrame.columns)) {
        val whereDataset = joinDataset.where(s"${baseCol} = ${compareCol}")
        result.append(new StringColumResult(baseCol, baseCount, compareCol, targetCount, whereDataset.count))
      }
    }
    else {

    }
    // 无论有无主键，都要得到各数值类型列的最大值、最小值、平均值、中位数// 无论有无主键，都要得到各数值类型列的最大值、最小值、平均值、中位数
    //    for (dtype <- base.dataset.dtypes) {
    //      if ("long" == dtype._2 || "int" == dtype._2 || "float" == dtype._2 || "double" == dtype._2) {
    //        val head = base.dataset.select("max(" + dtype._1 + "), min(" + dtype._1 + "), avg(" + dtype._1 + ")").head
    //        val maxValue = head.getLong(0)
    //        val minValue = head.getLong(1)
    //        val avgValue = head.getLong(2)
    //      }
    //    }
    //    for (dtype <- control.dataset.dtypes) {
    //      if ("long" == dtype._2 || "int" == dtype._2 || "float" == dtype._2 || "double" == dtype._2) {
    //        val head = control.dataset.select("max(" + dtype._1 + "), min(" + dtype._1 + "), avg(" + dtype._1 + ")").head
    //        val maxValue = head.getLong(0)
    //        val minValue = head.getLong(1)
    //        val avgValue = head.getLong(2)
    //      }
    //    }
    result
  }

}

