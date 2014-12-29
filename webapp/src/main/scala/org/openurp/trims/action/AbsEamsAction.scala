package org.openurp.trims.action

import org.beangle.data.jpa.dao.OqlBuilder
import org.beangle.data.model.Entity
import org.beangle.webmvc.entity.action.RestfulAction
import org.openurp.edu.base.Project
import org.openurp.edu.teach.lesson.Lesson
import org.openurp.base.Department
import org.beangle.webmvc.entity.action.AbstractRestfulAction
import org.beangle.webmvc.entity.action.AbstractEntityAction
import org.openurp.edu.base.Student
import org.beangle.commons.lang.Strings
import org.beangle.data.jpa.dao.SqlBuilder
import scala.collection.mutable.ListBuffer
import java.util.Calendar

abstract class AbsEamsAction[T <: Entity[_ <: java.io.Serializable]] extends AbstractEntityAction[T] {

  protected def getProject() = {
    entityDao.get(classOf[Project], new Integer(1))
  }

  protected def getDepartments() = {
    entityDao.findBy(classOf[Department], "teaching", Array(true))
  }

  protected def getAllDepartments() = {
    entityDao.getAll(classOf[Department])
  }

  protected def getDepartmentMap() = {
    val map = new collection.mutable.HashMap[String, String]
    entityDao.getAll(classOf[Department]).foreach(d => {
      map.put(d.id.toString(), if (d.shortName != null) d.shortName else d.name)
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

  protected def getAvg(datas: Seq[Any])(f: Any => Double) = {
    val o = (for (d <- datas) yield f(d))
    o.sum / datas.length
  }

  protected def getStandardDeviation(datas: Seq[Any], avg: Double)(f: Any => Double) = {
    val o = (for (d <- datas) yield (Math.pow(f(d) - avg, 2)))
    Math.sqrt(o.sum / datas.length)
  }

  protected def where[T](query: OqlBuilder[T], condition: String, name: String, value: Option[Any]) {
    if (value.isDefined && Strings.isNotBlank(value.get.toString) &&
      !(value.get.getClass.isPrimitive() && value.get.asInstanceOf[AnyVal] == 0)) {
      put(name, value.get)
      query.where(condition, value.get)
    }
  }

  protected def putNamesAndValues(datas: Seq[Any]) {
    putNamesAndValues(datas, data => data(0))
  }

  protected def putNamesAndValues(datas: Seq[Any], nf: Array[Any] => Any) {
    val names = for (data <- datas) yield nf(data.asInstanceOf[Array[Any]])
    val values = for (data <- datas) yield data.asInstanceOf[Array[Any]](1)
    put("names", names)
    put("values", values)
    put("datas", datas)
  }

  protected def getYears() :ListBuffer[Int]={
    val sql = """select min(_year)
        from(
        (select to_char(p.published_date,'YYYY') as _year
        from sin_harvest.thesis_harvests t
        join sin_harvest.published_situations p on p.id = t.published_situation_id)
        union
        (select to_char(publish_date,'YYYY') as _year
        from sin_harvest.literatures )) t"""
    val query = SqlBuilder.sql(sql)
    val startYear = new Integer(entityDao.search(query)(0).toString).toInt
    val years = new ListBuffer[Int]
    val curYear = Calendar.getInstance().get(Calendar.YEAR)
    for (year <- startYear to curYear) {
      years += year
    }
    years
  }

}