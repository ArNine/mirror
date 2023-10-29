package com.archer.mirror.mirrorcore.api

import com.archer.mirror.mirrorcore.common.{FileTaskFactory, HiveTaskFactory, SourceType, TaskFactory}
import com.oracle.webservices.internal.api.message.PropertySet.Property

import scala.reflect.runtime.universe._
import scala.reflect.runtime.{universe => ru}
class Application {

  def create(property: String): Unit = {
    // split first data source type and prop
    val baseSourceType = ""
    val compareSourceType = ""
    val baseDataFactory = createObjectByClassName(baseSourceType)
    val compareDataFactory = createObjectByClassName(compareSourceType)
    val baseSource = baseDataFactory.createSource(property)
    val compareSource = compareDataFactory.createSource(property)


  }


  def createObjectByClassName(className: String): TaskFactory = {
    val runtimeMirror: ru.Mirror = ru.runtimeMirror(getClass.getClassLoader)
    try {
      val classSymbol: ClassSymbol = runtimeMirror.staticClass(className)
      val classMirror: ru.ClassMirror = runtimeMirror.reflectClass(classSymbol)
      val constructorSymbol = classSymbol.primaryConstructor.asMethod
      val constructorMirror: MethodMirror = classMirror.reflectConstructor(constructorSymbol)
      val obj: Any = constructorMirror.apply()
      obj.asInstanceOf[TaskFactory]
    } catch {
      case e: ClassNotFoundException =>
        throw new IllegalArgumentException(s"Class not found: $className")
      case e: NoSuchMethodException =>
        throw new IllegalArgumentException(s"No suitable constructor found for class: $className")
      case e: Exception =>
        throw new IllegalArgumentException(s"Failed to create object of class: $className", e)
    }
  }


}
