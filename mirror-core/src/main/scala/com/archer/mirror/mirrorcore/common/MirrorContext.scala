package com.archer.mirror.mirrorcore.common

import org.apache.spark.sql.SparkSession
import org.apache.spark.{SparkConf, SparkContext}

object MirrorContext {

  private val sparkConf: SparkConf = new SparkConf().setMaster("local").setAppName("test")

  def getOrCreateSpark(): SparkSession = {
    SparkSession.builder().config(sparkConf).getOrCreate()
  }



}


