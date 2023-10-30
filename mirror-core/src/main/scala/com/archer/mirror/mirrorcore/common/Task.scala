package com.archer.mirror.mirrorcore.common

import com.archer.mirror.mirrorcore.source.{DataSource, FileSource}

trait TaskFactory {
  def createSource(property: String): DataSource
}

class FileTaskFactory extends TaskFactory {
  override def createSource(property: String): DataSource = {
    val fileType = "csv"
    val filePath = ""
    val datasetName = ""
    val columnMap = Map[String, String]()
    new FileSource(fileType, filePath, datasetName, columnMap)
  }
}

class HiveTaskFactory extends TaskFactory {
  override def createSource(property: String): DataSource = ???
}


