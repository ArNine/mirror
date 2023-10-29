package com.archer.mirror.mirrorcore.bean


trait Diff {
  def diff: Double
}

class StringColumResult(baseColName: String, baseColCount: Long, controlColName: String, controlColCount: Long, sameCount: Long) extends Diff {
  override def diff: Double = 1.0 - ((2.0 * sameCount) / (baseColCount + controlColCount))
}


class NumericColumResult(baseColName: String, baseColCount: Long, controlColName: String, controlColCount: Long, sameCount: Long)
