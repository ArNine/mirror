package com.archer.mirror.mirrorcore.api

import com.archer.mirror.mirrorcore.bean.Diff

import scala.collection.mutable.ListBuffer


class MirrorSession(property: String) {

  def exec(): ListBuffer[Diff] = {
    val application = new Application()
    val compare = application.create(property)
    compare.compare
  }


}


object MirrorSession {
  def createMirrorSession(property: String): Unit = {
    new MirrorSession(property)
  }

}
