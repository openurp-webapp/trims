package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.teach.core.Project
import org.openurp.teach.lesson.Lesson
import org.openurp.base.Department
import org.beangle.webmvc.entity.action.AbstractRestfulAction
import org.beangle.webmvc.entity.action.AbstractEntityAction
import org.openurp.teach.core.Student
import org.beangle.commons.lang.Strings

abstract class AbsEamsAction[T <: Entity[_ <: java.io.Serializable]] extends AbstractEntityAction[T] {

  protected def getProject() = {
    entityDao.get(classOf[Project], new Integer(1))
  }
  
  protected def getDepartments() = {
    entityDao.findBy(classOf[Department], "teaching", Array(true))
  }
  
  protected def getDepartmentMap() = {
    val map = new collection.mutable.HashMap[String, String]
    getDepartments().foreach( d => {
      map.put(d.id.toString(), if(d.shortName != null) d.shortName else d.name)
    })
    map
  }

  protected def getLessonYears() = {
    val query = OqlBuilder.from(classOf[Lesson], "l")
    query.select("l.semester.schoolYear")
    query.groupBy("l.semester.schoolYear")
    query.orderBy("l.semester.schoolYear desc")
    entityDao.search(query)
  }

  protected def getStudentGrade() = {
    val query = OqlBuilder.from(classOf[Student], "s")
    query.select("s.grade")
    query.groupBy("s.grade")
    query.orderBy("s.grade")
    entityDao.search(query)
  }
  
  protected def getAvg(datas:Seq[Any])(f:Any => Double) = {
    val o = (for(d <- datas) yield f(d))
    o.sum / datas.length
  }
  
  protected def getStandardDeviation(datas:Seq[Any], avg:Double)(f:Any => Double) = {
    val o = (for(d <- datas) yield (Math.pow(f(d) - avg, 2)))
    Math.sqrt(o.sum / datas.length)
  }
  
  protected def where[T](query:OqlBuilder[T], condition:String, name:String, value:Any) {
    if(value != null && Strings.isNotBlank(value.toString) && (value.getClass.isPrimitive() && value.asInstanceOf[AnyVal] != 0)){
      put(name, value)
      query.where(condition, value)
    }
  }
  
  protected def putNamesAndValues(datas:Seq[Any]){
    val names = for(data <- datas) yield data.asInstanceOf[Array[Any]](0)
    val values = for(data <- datas) yield data.asInstanceOf[Array[Any]](1)
    put("names", names)
    put("values", values)
    put("datas", datas)
  }

}