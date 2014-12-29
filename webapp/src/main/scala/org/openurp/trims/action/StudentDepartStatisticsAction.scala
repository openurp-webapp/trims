package org.openurp.trims.action

import org.beangle.commons.lang.Strings
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department
import org.openurp.edu.base.Student
import org.openurp.edu.teach.lesson.Lesson
import java.util.Date

class StudentDepartStatisticsAction extends AbsEamsAction[Student] {

  def index(): String = {
    forward()
  }

  def year(): String = {
    val query = OqlBuilder.from(classOf[Student], "s")
    query.select("substring(s.grade, 1, 4), count(*) as num")
    where(query, "s.department.id = :did", "departmentId", getInt("departmentId"))
    query.where("substring(s.grade, 1, 4) > '2003'")
    query.groupBy("substring(s.grade, 1, 4)")
    query.orderBy("substring(s.grade, 1, 4)")
    putNamesAndValues(entityDao.search(query))
    forward()
  }

  def yearDepart(): String = {
    put("year", get("year").get)
    depart()
  }

  def depart(): String = {
    val query = OqlBuilder.from(classOf[Student], "s")
    query.select("s.department.id, count(*) as num")
    if(get("year").isEmpty){
      query.where("s.graduateOn >:now", new Date())
    }
    where(query, "substring(s.grade, 1, 4) =:year", "year", get("year"))
    query.groupBy("s.department.id")
    query.orderBy("count(*) desc")
    val datas = entityDao.search(query).asInstanceOf[Seq[Any]]
    val departs = getDepartmentMap()
    val names = for (data <- datas) yield departs.get(data.asInstanceOf[Array[Any]](0).toString).getOrElse("")
    val values = for (data <- datas) yield data.asInstanceOf[Array[Any]](1)
    put("datas", datas)
    put("names", names)
    put("values", values)
    forward()
  }

  def departYear(): String = {
    val department = entityDao.get(classOf[Department], new Integer(getInt("departmentId").get))
    put("department", department)
    year()
  }
}