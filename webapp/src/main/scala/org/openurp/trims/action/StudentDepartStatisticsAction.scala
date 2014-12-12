package org.openurp.trims.action

import org.beangle.commons.lang.Strings
import org.beangle.data.jpa.dao.OqlBuilder
import org.openurp.base.Department
import org.openurp.teach.core.Student
import org.openurp.teach.lesson.Lesson

class StudentDepartStatisticsAction extends AbsEamsAction[Student] {

  def index(): String = {
    forward()
  }

  def year(): String = {
    val query = OqlBuilder.from(classOf[Student], "s")
    query.select("substring(s.grade, 0, 5), count(*) as num")
    where(query, "s.department.id = :did", "departmentId", getInt("departmentId"))
    query.groupBy("substring(s.grade, 0, 5)")
    query.orderBy("substring(s.grade, 0, 5)")
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
    where(query, "substring(s.grade, 1, 4) =:year", "year", get("year"))
    query.groupBy("s.department.id")
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